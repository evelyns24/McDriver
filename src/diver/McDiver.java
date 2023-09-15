package diver;

import game.*;
import graph.ShortestPaths;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.List;


/** This is the place for your implementation of the {@code SewerDiver}.
 */
public class McDiver implements SewerDiver {

//    /**
//     * previous: the id of the previous tile that McDriver walked on
//     */
    // private Long previous;

    /**
     * See {@code SewerDriver} for specification.
     */
    @Override
    public void seek(SeekState state) {
        // TODO : Look for the ring and return.
        // DO NOT WRITE ALL THE CODE HERE. DO NOT MAKE THIS METHOD RECURSIVE.
        // Instead, write your method (it may be recursive) elsewhere, with a
        // good specification, and call it from this one.
        //
        // Working this way provides you with flexibility. For example, write
        // one basic method, which always works. Then, make a method that is a
        // copy of the first one and try to optimize in that second one.
        // If you don't succeed, you can always use the first one.
        //
        // Use this same process on the second method, scram.
        ArrayList<Long> visits = new ArrayList<>();
        //  previous=state.currentLocation();
        //dfsWalk(state, visits);
        //   int min = 10000;
        //   Long m = Long.valueOf(0);
        dfsWalk(state, visits);
        //    dfsWalk3(state, visits);
    }

    /**
     * Private Helper Method for method seek. McDiver is standing on a location (Long loc) given by
     * SeekState state. Visit location reachable (in order of distance from ring) along paths of
     * unvisited locations from original location loc. End with McDiver standing on loc.
     *
     * @param state: the state of the game while finding the ring. visits: ArrayList that keeps
     *               track of all of the Long ids that have been visited
     */
    private void dfsWalk(SeekState state, ArrayList<Long> visits) {
        Long loc = state.currentLocation();
        //  Map<SeekState, Boolean> visits = new HashMap<>();
        //   ArrayList<Long>visits = new ArrayList<>();
        visits.add(loc);
        //  state.moveTo(loc);
        for (NodeStatus node : state.neighbors().stream().sorted().toList()) {
            // for(NodeStatus node: state.neighbors()){
            //   if(state.distanceToRing() == 0){ return;}
            if (!visits.contains(node.getId())) {
                Long l = node.getId();
                state.moveTo(l);
                if (state.distanceToRing() == 0) {
                    return;
                }
//            try {
//                state.moveTo(l);
//            }catch(IllegalArgumentException e) {}
                dfsWalk(state, visits);
                if (state.distanceToRing() == 0) {
                    return;
                }
                state.moveTo(loc);
                //  if(state.distanceToRing() == 0){ return;}
            }
        }
    }

//    private void dfsWalk2(SeekState state, ArrayList<Long> visits){
//        Long loc = state.currentLocation();
//        visits.add(loc);
//        for(NodeStatus node: state.neighbors()){
//
//            if(!visits.contains(node.getId())){
//                Long l = node.getId();
//         //       state.moveTo(findMin(state, visits));
//         //       System.out.println(findMin(state));
//                if(state.distanceToRing() == 0){ return;}
//                dfsWalk2(state, visits);
//                if(state.distanceToRing() == 0){ return;}
//                state.moveTo(l);
//            }
//        }
//    }

//    private void dfsWalk3(SeekState state, ArrayList<Long> visits){
//        Long loc = state.currentLocation();
//        if(!visits.contains(loc)) visits.add(loc);
//     //   System.out.println("size: " + visits.size());
//        boolean cover = true;
//        for(NodeStatus n: state.neighbors()){
//            if(!visits.contains(n.getId())) cover = false;
//       }
//        Long m=findMin(state, visits, cover);
//        state.moveTo(m);
////        while(cover){
////            Long a=findMin(state, visits, cover);
////            state.moveTo(a);
//       //     previous = loc;
////            for(NodeStatus n: state.neighbors()){
////                if(!visits.contains(n.getId())) cover = false;
////            }
//     //   }
//        previous = loc;
//        if(state.distanceToRing() == 0){ return;}
//        dfsWalk3(state, visits);
//        if(state.distanceToRing() == 0){ return;}
//        previous = state.currentLocation();
//        System.out.println("here");
//        state.moveTo(loc);
//    }

//    private void dfsWalk2(SeekState state, ArrayList<Long> visits){
//        Long loc = state.currentLocation();
//        visits.add(loc);
//        Object[] array = state.neighbors().toArray();
//        Arrays.sort(array);
//        for(Object n: array){
//            NodeStatus node = (NodeStatus) n;
//            if(!visits.contains(node.getId())){
//                Long l = node.getId();
//                state.moveTo(l);
//                if(state.distanceToRing() == 0){ return;}
//                dfsWalk(state, visits);
//                if(state.distanceToRing() == 0){ return;}
//                state.moveTo(loc);
//            }
//        }
//    }

//    private Long findMin(SeekState state, ArrayList<Long> l, boolean cover){
//   //     System.out.println("in");
//        int min = 100000;
//        Long value = state.neighbors().iterator().next().getId();
//        Object[] array = state.neighbors().toArray();
//       // ArrayList<Object> a = java.util.Arrays.asList(array);
////        int rand = new Random().nextInt(array.length);
////        Long value = ((NodeStatus) array[rand]).getId();
////        boolean cover = true;
////        for(NodeStatus n: state.neighbors()){
////            if(!l.contains(n)) cover = false;
////        }
//    //    for(NodeStatus node: state.neighbors()){
//        for(int i=0; i<array.length; i++){
//            NodeStatus node = ((NodeStatus)array[i]);
//            int dist = node.getDistanceToRing();
//        //    if(dist <= min && (!l.contains(node.getId()) || array.length!= 1)){
//          // if(dist <= min && !l.contains(node.getId())){
//            if(dist <= min){
//                if (previous!=node.getId() || array.length==1) {
//                    // System.out.println("in");
//               //     System.out.println("cover = " + cover);
//                    min = dist;
//                    if (!l.contains(node.getId()) || array.length == 1 || array.length == 2) {
////                    if(!l.contains(node.getId()) || array.length==1 || array.length==2 || array.length==4)
//                        value = node.getId();
//
//                        if(cover){
//                           // value = state.neighbors().iterator().next().getId();
//                        //    System.out.println("in cover");
//                            value = ((NodeStatus)array[(i)% array.length]).getId();
//                       //     System.out.println("value: " + value);
//                          //  int rand = new Random().nextInt(array.length);
//                          //  value = ((NodeStatus) array[rand]).getId();
//                        }
//
//                    }
//                }
//
//            }
////            else{
////                max = dist;
////            }
//        }
////        int i = 0;
////        while(previous==value && array.length>1){
////            NodeStatus n = (array[1]
////        }
//     //   System.out.println("min: " + min);
//     //   System.out.println("value: " + value);
//        return value;
//    }


    /**
     * See {@code SewerDriver} for specification.
     */
    @Override
    public void scram(ScramState state) {
        // TODO: Get out of the sewer system before the steps are used up.
        // DO NOT WRITE ALL THE CODE HERE. Instead, write your method elsewhere,
        // with a good specification, and call it from this one.
     //   ArrayList<Node> reach = new ArrayList<>();
     //   java.util.List<Edge> list = findPath(state);
      //  int length = list.get(0).length();
      //  System.out.println("length: " + length);
        //walk2(state,reach,length);
        Object[] o = state.allNodes().toArray();
        Node[] n = new Node[o.length];
        for (int i = 0; i < o.length; i++) {
            n[i] = (Node) o[i];
        }
        java.util.Set<Node> s = new HashSet<Node>(Arrays.asList(n));
        Maze m = new Maze(s);
      //  Maze m = new Maze()
        walk(state, m);
    }

    /**
     * The original, working version that only takes shortest path through the maze.
     * (McDiver is always able to exit)
     * @param state: the game state that allows us access to the entire graph
     */

    private void scramming(ScramState state) {
        Object[] o = state.allNodes().toArray();
        Node[] n = new Node[o.length];
        for (int i = 0; i < o.length; i++) {
            n[i] = (Node) o[i];
        }
        java.util.Set<Node> s = new HashSet<Node>(Arrays.asList(n));
        Maze m = new Maze(s);
        ShortestPaths path = new ShortestPaths(m);
        path.singleSourceDistances(state.currentNode());
        java.util.List<Edge> list = path.bestPath(state.exit());
        for (Edge e : list) {
            state.moveTo(e.destination());
        }

    }

//    private void scramming2(ScramState state, ArrayList<Long> reach) {
//        while(leave(state)){
//            //travel along path with most coins
//            Node current = state.currentNode();
//            for (Node node : state.currentNode().getNeighbors().stream().sorted().toList()) {
//                if (!reach.contains(node.getId())) {
//                   // Long l = node.getId();
//                    state.moveTo(node);
////                    if (state.distanceToRing() == 0) {
////                        return;
////                    }
//                    //  if(state.distanceToRing() == 0){ return;}
//                }
//            }
//            state.moveTo(current);
//        }
//        java.util.List<Edge> list = findPath(state);
//        for (Edge e : list) {
//            state.moveTo(e.destination());
//        }
//    }

//    private void walk(ScramState state, ArrayList<Node> reach, int length){
////        if (!leave(state)){
////            System.out.println("in BS");
////            java.util.List<Edge> list = findPath(state);
////            for (Edge e : list) {
////                state.moveTo(e.destination());
////            }
////            //return;
////        }
//        Node current = state.currentNode();
//        reach.add(current);
//        java.util.List<Node> l=state.currentNode().getNeighbors().stream().sorted().toList();
//        //l.
//        for (Node node : l) {
//            if (!reach.contains(node.getId())) {
//                if(!leave(state, length)) {
//                    System.out.println("in 0");
//                    java.util.List<Edge> list = findPath(state);
//                    System.out.println("list size: " + list.size());
//                    for (Edge e : list) {
//                        state.moveTo(e.destination());
//                    }
//                  //  System.out.println("return");
//                    return;
//                }
//                // Long l = node.getId();
//                state.moveTo(node);
//                if(!leave(state, length)) {
//                    System.out.println("in 1");
//                    java.util.List<Edge> list = findPath(state);
//                    System.out.println("list size: " + list.size());
//                    for (Edge e : list) {
//                        state.moveTo(e.destination());
//                    }
//                //    System.out.println("return");
//                    return;
//                }
//                walk(state,reach, length);
//              //  state.moveTo(current);
//                if(!leave(state, length)) {
//                    System.out.println("in 2");
//                    java.util.List<Edge> list = findPath(state);
//                    for (Edge e : list) {
//                        state.moveTo(e.destination());
//                    }
//                    break;
//                }
//                state.moveTo(current);
//            }
//        }
//        System.out.println("here");
//     //   state.moveTo(current);
//
//    }
//
//    private void walk2(ScramState state, ArrayList<Node> reach, int length){
//        Node current = state.currentNode();
//        reach.add(current);
//        java.util.List<Node> l=state.currentNode().getNeighbors().stream().sorted().toList();
//        //l.
//        for (Node node : l) {
//            if (!reach.contains(node.getId())) {
//                if(!leave(state, length)) {
//                    System.out.println("in 0");
//                    java.util.List<Edge> list = findPath(state);
//                    System.out.println("list size: " + list.size());
//                    for (Edge e : list) {
//                        state.moveTo(e.destination());
//                    }
//                    //  System.out.println("return");
//                    return;
//                }
//                // Long l = node.getId();
//                state.moveTo(node);
//                if(!leave(state, length)) {
//                    System.out.println("in 1");
//                    java.util.List<Edge> list = findPath(state);
//                    System.out.println("list size: " + list.size());
//                    for (Edge e : list) {
//                        state.moveTo(e.destination());
//                    }
//                    //    System.out.println("return");
//                    break;
//                }
//                walk2(state,reach, length);
//                //  state.moveTo(current);
//                if(!leave(state, length)) {
//                    System.out.println("in 2");
//                    java.util.List<Edge> list = findPath(state);
//                    for (Edge e : list) {
//                        state.moveTo(e.destination());
//                    }
//                    break;
//                }
//                state.moveTo(current);
//            }
//        }
//        System.out.println("here");
//        //   state.moveTo(current);
//
//    }
//
//

    /**
     * The optimized version where McDiver tries to find coins of most value and collect them
     * while also exiting the maze in the required number of steps.
     * @param state: the game state that allows us access to the entire graph
     */
//    private void walk (ScramState state){
//        List<Node> l=state.allNodes().stream().toList();
//        ArrayList a = new ArrayList<>(l);
//     //   a.remove(state.exit());
//        Collections.sort(a,new java.util.Comparator<Node>() {
//            public int compare(Node n1, Node n2) {
//                if (n1.getTile().coins() == n2.getTile().coins())
//                    return Long.compare(n1.getId(), n2.getId());
//                return n2.getTile().coins() - n1.getTile().coins();
//            }
//        });
//        int size = l.size();
//        Node max=l.get(0);
//        int i = 0;
//        while(!leave(state,state.currentNode(),max) && i<size){
//            List<Edge> edges = findPath(state, state.currentNode(), max);
//            for(Edge e: edges){
//                state.moveTo(e.destination());
//            }
//           // state.moveTo(max);
//            i++;
//            max = l.get(i);
//        }
//        List<Edge> ed = findPath(state, state.currentNode(), state.exit());
//        for(Edge e: ed){
//            state.moveTo(e.destination());
//        }
//    }

//    private void walk (ScramState state){
//        List<Node> l=state.allNodes().stream().toList();
//        ArrayList a = new ArrayList<>(l);
//        //   a.remove(state.exit());
//
////        int size = l.size();
//      //  Node max=l.get(0);
//        Node max = state.currentNode();
//  //      int i = 0;
//        while(!leave(state,state.currentNode(),max)){
//           a.remove(state.currentNode());
//            Collections.sort(a,new java.util.Comparator<Node>() {
//                public int compare(Node n1, Node n2) {
//                   // if (n1.getTile().coins() == n2.getTile().coins())
//                   //     return Long.compare(n1.getId(), n2.getId());
//                    List<Edge> p1 = findPath(state, state.currentNode(), n1);
//                 //   System.out.println("p1 size: " + p1.size());
//                    List<Edge> p2 = findPath(state, state.currentNode(), n2);
////                    if(state.currentNode().equals(n1)|| state.currentNode().equals(n2)){
////                        return -10000;
////                    }
//                 //   System.out.println("p2 size: " + p2.size());
//                    int s1 = 0, s2 = 0;
//                    for(Edge e: p1){
//                        s1 += e.length;
//                    }
//                    for(Edge e: p2){
//                        s2 += e.length;
//                    }
//                    return ((n2.getTile().coins())/s2) - (n1.getTile().coins()/s1);
//                }
//            });
//          //  a.add(state.currentNode());
//        //    a = new ArrayList<>(l);
//            max = (Node) a.get(0);
//            List<Edge> edges = findPath(state, state.currentNode(), max);
//            for(Edge e: edges){
//                state.moveTo(e.destination());
//            }
//            a = new ArrayList<>(l);
//            // state.moveTo(max);
////            i++;
////            max = l.get(i);
//        }
//        List<Edge> ed = findPath(state, state.currentNode(), state.exit());
//        for(Edge e: ed){
//            state.moveTo(e.destination());
//        }
//    }

    private void walk (ScramState state, Maze m){
        List<Node> l=state.allNodes().stream().toList();
        ArrayList a = new ArrayList<>(l);
        //   a.remove(state.exit());

//        int size = l.size();
        //  Node max=l.get(0);
     //   sort(state, a);
        Node max = (Node) a.get(0);
        //      int i = 0;
        while(!leave(state,state.currentNode(),max,m)){

            a.remove(state.currentNode());
            sort(state, a, m);

            //  a.add(state.currentNode());
            //    a = new ArrayList<>(l);
            max = (Node) a.get(0);
            List<Edge> edges = findPath(state, state.currentNode(), max, m);
            if(leave(state,state.currentNode(),max,m)) break;
            for(Edge e: edges){
                state.moveTo(e.destination());
            }
            a = new ArrayList<>(l);
            // state.moveTo(max);
//            i++;
//            max = l.get(i);
        }
        List<Edge> ed = findPath(state, state.currentNode(), state.exit(),m);
        for(Edge e: ed){
            state.moveTo(e.destination());
        }
    }

    /**
     * Returns true when McDiver should begin his shortest path to exit
     * @param state: the game state that allows us access to the entire graph
     * @param cur: the current node that McDiver is at
     * @param dest: the desired node that we want McDiver to be at
     * @return true when McDiver should begin his shortest path to exit
     */
    private boolean leave(ScramState state, Node cur, Node dest, Maze m) {
        List<Edge> list1 = findPath(state, cur, dest, m);
        int sum1 = 0;
        for(Edge e: list1){
            sum1 += e.length;
        }
        List<Edge> list2 = findPath(state, dest, state.exit(), m);
        int sum2 = 0;
        for(Edge e: list2){
            sum2 += e.length;
        }
        return (sum1 + sum2) > state.stepsToGo();
    }

    /**
     * Private helper method that finds the shortest path from current to dest using
     * shortest path implementation from part 1
     * @param state: the game state that allows us access to the entire graph
     * @param cur: the current node McDiver is standing on
     * @param dest: the desired node that we want McDiver to be at
     * @return: List of Edges detailing the shortest path to dest
     */

    private List<Edge> findPath(ScramState state, Node cur, Node dest, Maze m){
        Object[] o = state.allNodes().toArray();
        Node[] n = new Node[o.length];
        for (int i = 0; i < o.length; i++) {
            n[i] = (Node) o[i];
        }
        java.util.Set<Node> s = new HashSet<Node>(Arrays.asList(n));
    //    Maze m = new Maze(s);
        ShortestPaths path = new ShortestPaths(m);
        path.singleSourceDistances(cur);
        java.util.List<Edge> list = path.bestPath(dest);
        return list;
    }

    private void sort(ScramState state, ArrayList a, Maze m){
        Collections.sort(a,new java.util.Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                // if (n1.getTile().coins() == n2.getTile().coins())
                //     return Long.compare(n1.getId(), n2.getId());
                List<Edge> p1 = findPath(state, state.currentNode(), n1, m);
                //   System.out.println("p1 size: " + p1.size());
                List<Edge> p2 = findPath(state, state.currentNode(), n2, m);
//                    if(state.currentNode().equals(n1)|| state.currentNode().equals(n2)){
//                        return -10000;
//                    }
                //   System.out.println("p2 size: " + p2.size());
                int s1 = 0, s2 = 0;
                for(Edge e: p1){
                    s1 += e.length;
                }
                for(Edge e: p2){
                    s2 += e.length;
                }
                return ((n2.getTile().coins())/s2) - (n1.getTile().coins()/s1);
            }
        });
    }

}





//    private boolean leave(ScramState state, Node dest){
//        java.util.List<Edge> list = findPath(state,dest);
//        int dis1=0;
//        for (Edge e:list){
//            dis1=dis1+e.length();
//        }
//        java.util.List<Edge> list2 = findPath(dest);
//        int l = 0;
//        if(list.size()==0){
//            return false;
//        }
//        else{
//            l = list.get(0).length();
//        }
//        System.out.println("size: " + list.size()*length + " , steps: " + state.stepsToGo());
//        //return list.size()*8 < state.stepsToGo();
//        //return state.stepsToGo()-list.size()*length>= length;
//        return state.stepsToGo()/(l)-list.size() >= 1;
//    }
//
//    private java.util.List<Edge> findPath(ScramState state, Node dest){
//        Object[] o = state.allNodes().toArray();
//        Node[] n = new Node[o.length];
//        for (int i = 0; i < o.length; i++) {
//            n[i] = (Node) o[i];
//        }
//        java.util.Set<Node> s = new HashSet<Node>(Arrays.asList(n));
//        Maze m = new Maze(s);
//        ShortestPaths path = new ShortestPaths(m);
//        path.singleSourceDistances(state.currentNode());
//        java.util.List<Edge> list = path.bestPath(dest);
//        return list;
//    }
//
//}
