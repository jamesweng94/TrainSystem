import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
class Trains {
    public static void main(String[] args) throws IOException {

        Digraph graph = new Digraph();
        List<Integer> output = new ArrayList<>();
        BufferedReader inputBuffer = new BufferedReader(new FileReader("inputs/init.txt"));
        BufferedReader distanceBuffer = new BufferedReader(new FileReader("inputs/input1.txt"));
        BufferedReader maxStopBuffer = new BufferedReader(new FileReader("inputs/input2.txt"));
        BufferedReader exactStopBuffer = new BufferedReader(new FileReader("inputs/input3.txt"));
        BufferedReader shortestPathBuffer = new BufferedReader(new FileReader("inputs/input4.txt"));
        BufferedReader maxDistanceBuffer = new BufferedReader(new FileReader("inputs/input5.txt"));


        try{
            //init graph
            String input = inputBuffer.readLine();
            while (input != null) {
                graph.add(input.charAt(0), input.charAt(1), Character.getNumericValue(input.charAt(2)));
                input = inputBuffer.readLine();
            }


            // Distance of the route
            String route = distanceBuffer.readLine();
            while (route != null) {
                output.add(RouteDistance(graph, route));
                route = distanceBuffer.readLine();
            }

            //Number of trips with max stops
            String trip = maxStopBuffer.readLine();
            while(trip != null){
                int maxTemp = graph.tripWithMaxStop(trip.charAt(0), trip.charAt(1), Character.getNumericValue(trip.charAt(2)));
                if(maxTemp <= 0) output.add(-1);
                else output.add(maxTemp);
                trip = maxStopBuffer.readLine();
            }
            
            //Number of trips with exact stops
            String queryExact = exactStopBuffer.readLine();
            while(queryExact != null){
                int exactTemp = graph.exactStops(queryExact.charAt(0), queryExact.charAt(1), Character.getNumericValue(queryExact.charAt(2)));
                if(exactTemp <= 0) output.add(-1);
                else output.add(exactTemp);
                queryExact = exactStopBuffer.readLine();
            }

            //Shortest path between two locations
            String findShortest = shortestPathBuffer.readLine();
            while(findShortest != null){
                output.add(graph.shortestPath(findShortest.charAt(0), findShortest.charAt(1)));
                graph.reset();
                findShortest = shortestPathBuffer.readLine();
            }

            //find paths with distance limit
            String distanceLimit = maxDistanceBuffer.readLine();
            while(distanceLimit != null){
                String[] tokens = distanceLimit.split(" ");
                int distanceTemp = graph.maxDistance(tokens[0].charAt(0),tokens[0].charAt(1), Integer.parseInt(tokens[1]));
                if(distanceTemp <= 0) output.add(-1);
                else output.add(distanceTemp);
                distanceLimit = maxDistanceBuffer.readLine();
            }


            for(int i = 0; i < output.size(); ++i){
                if(output.get(i) < 0){
                    System.out.println("Output #" + (i+1) + ": NO SUCH ROUTE");
                }else{
                    System.out.println("Output #" + (i+1) + ": " + output.get(i));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch(ArrayIndexOutOfBoundsException exception){
            System.out.println("ARRAY OUT OF BOUNDS");
        }catch(StringIndexOutOfBoundsException stringException){
            System.out.println("STRING INDEX OUT OUT OF BOUNDS");
        }finally{
            inputBuffer.close();
            distanceBuffer.close();
            maxStopBuffer.close();
            exactStopBuffer.close();
            shortestPathBuffer.close();
            maxDistanceBuffer.close();
        }
    }

    static int RouteDistance(Digraph graph, String route){
        int totalDistance = 0;
        for(int i = 1; i < route.length(); ++i){
            int cur = graph.getDistance(route.charAt(i - 1), route.charAt(i));
            if(cur < 0){
                return -1;
            }else{
                totalDistance += cur;
            }
        }
        return totalDistance;
    }
}