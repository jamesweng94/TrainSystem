import java.util.*;

class Digraph<T extends Comparable<T>>{

    //Class Object - Vertex
    class Vertex{
        T value;
        boolean visited;
        int minDistance;
        Vertex prev;
        private List<Vertex> incoming;
        private List<Vertex> outgoing;
    
        public Vertex(T value){
            this.value = value;
            this.visited = false;
            this.minDistance = Integer.MAX_VALUE;
            this.prev = null;
            incoming = new ArrayList<>();
            outgoing = new ArrayList<>();
    
        }
        public void addIncoming(Vertex vex){
            incoming.add(vex);
        }
    
        public void addOutgoing(Vertex vex){
            outgoing.add(vex);
        }
    
        public T getValue(){
            return value;
        }

        public int compareTo(Vertex v2){
            return this.value.compareTo(v2.value);
        }
    }

    //Class Object Edge
    class Edge{
        Vertex from;
        Vertex to;
        int cost;
    
        public Edge(T v1, T v2, int cost){
    
            from = findVertex(v1);
            to = findVertex(v2);
    
            if(from == null){
                from = new Vertex(v1);
                vertices.add(from);
            }
    
            if(to == null){
                to = new Vertex(v2);
                vertices.add(to);
            }
            this.cost = cost;
    
            from.addOutgoing(to);
            to.addIncoming(from);
        }
    
        public Vertex getFromVertex(){
            return from;
        }
    
        public Vertex getToVertex(){
            return to;
        }
    }

    //Digraph private variable
    private List<Vertex> vertices;
    private List<Edge> edges;

    public Digraph(){
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void add(T from, T to, int cost){
        Edge tempEdge = findEdge(from, to);
        if(tempEdge == null){
            Edge e = new Edge(from, to, cost);
            edges.add(e);
        }else{
            tempEdge.cost = cost;
        }
    }

    private Vertex findVertex(T v){
        for(Vertex _v : vertices){
            if(_v.value.compareTo(v) == 0){
                return _v;
            }
        }
        return null;
    }

    private Edge findEdge(Vertex v1, Vertex v2)
	{
		for (Edge e : edges){
			if (e.from.equals(v1) && e.to.equals(v2)){
				return e;
			}
		}
		return null;
    }
    
    private Edge findEdge(T from, T to){
        for(Edge e : edges){
            if(e.from.value.equals(from) && e.to.value.equals(to)){
                return e;
            }
        }
        return null;
    }

    public int getDistance(T from, T to){
        Vertex start = findVertex(from);
        Vertex des = findVertex(to);
        Edge dis = findEdge(start, des);
        if(dis != null){
            return dis.cost;
        }
        return -1;
    }

    public int getDistance(Vertex from, Vertex to){
        Edge dis = findEdge(from, to);
        if(dis != null){
            return dis.cost;
        }
        return -1;
    }

    //Find number of paths with max stops
    public int tripWithMaxStop(T from, T to, int limit){
        Vertex start = findVertex(from);
        Vertex end = findVertex(to);
        if(start == null || end == null) return -1;
        return maxStopHelper(start, end, 0, limit);
    }

    public int maxStopHelper(Vertex from, Vertex des, int depth, int limit){
        if(depth > limit) return 0;
        if(depth == limit && !from.value.equals(des.value)) return 0; 
        int res = 0;
        from.visited = true;
        for(Vertex v : from.outgoing){
            if(v.value.equals(des.value)){
                    res++;
            }else if(v.visited == false){
                depth++;
                res+=maxStopHelper(v, des, depth, limit);
                depth--;
            }
        }
        from.visited = false;
        return res;
    }

    //Find number of paths with exact stops
    public int exactStops(T from, T to, int limit){
        Vertex start = findVertex(from);
        Vertex end = findVertex(to);
        if(start == null || end == null) return -1;
        return exactStopHelper(start, end, 0, limit - 1);
    }

    public int exactStopHelper(Vertex from, Vertex des, int depth, int limit){
        if(depth > limit)  return 0;
        int res = 0;
        for(Vertex v : from.outgoing){
            if(v.value.equals(des.value) && depth == limit){
                    res++;
            }else{
                depth++;
                res+=exactStopHelper(v, des, depth, limit);
                depth--;
            }
        }
        return res;
    }
    
    //shortest path
    public int shortestPath(T from, T to){
        reset();
        Vertex start = findVertex(from);
        Vertex end = findVertex(to);
        if(start == null || end == null) return -1;
        shortestPathHelper(start, end);
        if(end.minDistance == Integer.MAX_VALUE || end.minDistance == 0){
            return -1;
        }
        return end.minDistance;
    }

    public void shortestPathHelper(Vertex start, Vertex end){
        PriorityQueue<Vertex> pq = new PriorityQueue<>((a,b) -> (a.minDistance - b.minDistance));
        if(!start.value.equals(end.value)){
            start.minDistance = 0;
        }
        pq.add(start);
        while(!pq.isEmpty()){
            Vertex temp = pq.poll();
            for(Vertex v : temp.outgoing){
                int weight = getDistance(temp, v);
                int dist = start.value.equals(end.value) && end.value.equals(temp.value) ? 0 : temp.minDistance;
                dist += weight;
                if(dist < v.minDistance){
                    v.minDistance = dist;
                    v.prev = temp;
                    pq.add(v);             
                }
            }
        }
    }

    public void reset(){
        for(Vertex v : vertices){
            v.visited = false;
            v.minDistance = Integer.MAX_VALUE;
            v.prev = null;
        }
    }

    //Find path with max distance
    public int maxDistance(T from, T to, int max){
        Vertex start = findVertex(from);
        Vertex end = findVertex(to);
        if(start == null || end == null) return -1;
        return maxDistanceHelper(start, end, 0, max);
    }

    public int maxDistanceHelper(Vertex start, Vertex end, int total, int max){
        if(total >= max) return 0;
        int res = 0;
        for(Vertex v : start.outgoing){
            int weight = getDistance(start, v);
            if(v.value.equals(end.value) && (total + weight) < max){
                total += weight;
                res++;
                res += maxDistanceHelper(v, end, total, max);
            }else{
                total += weight;
                res += maxDistanceHelper(v, end, total, max);
                total -= getDistance(start, v);
            }
        }
        return res;
    }
}