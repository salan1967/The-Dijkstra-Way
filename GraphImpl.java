package a6;

import java.util.*;

public class GraphImpl implements Graph {
    Map<String, Node> nodes; //Do not delete.  Use this field to store your nodes.
    // key: name of node. value: a5.Node object associated with name
    Map<String, List<EdgeImpl>> dgs;
    private int numONodes = 0;
    private int numOEdges = 0;

    public GraphImpl() {
        nodes = new HashMap<>();
        dgs = new HashMap<>();
    }

    @Override
    public boolean addNode(String name) {

        if (nodes.containsKey(name)) {
            return false;
        } else {
            NodeImpl nod = new NodeImpl(name);
            nodes.put(name, nod);
            numONodes++;
            return true;
        }
    }

    @Override
    public boolean addEdge(String src, String dest, double weight) {
        if (weight < 0 || !nodes.containsKey(dest) || !nodes.containsKey((src))) {
            return false;
        }
        if (dgs.get(src) != null) {
            for (EdgeImpl i : dgs.get(src)) {
                if (dest.equals(i.getdest())) {
                    return false;
                }

            }
        }
        EdgeImpl dg = new EdgeImpl(weight, dest);

        List<EdgeImpl> lst;
        if (dgs.containsKey(src)) {
            lst = dgs.get(src);
            lst.add(dg);
        } else {
            lst = new ArrayList<EdgeImpl>();
            lst.add(dg);
        }
        dgs.put(src, lst);
        numOEdges += 1;
        return true;
        //Dummy return value.  Remove when you implement!
    }

    @Override
    public boolean deleteNode(String name) {
        //Hint: Do you need to remove edges when you delete a node?

        if (nodes.containsKey(name)) {
            numONodes--;
            nodes.remove(name);
            return true;
        }

        return false;


    }

    @Override
    public boolean deleteEdge(String src, String dest) {
        List<EdgeImpl> lst = dgs.get(src);
        for (EdgeImpl tmp : lst) {
            if (tmp.getdest() == dest) {
                lst.remove(tmp);
                dgs.put(src, lst);
                return true;
            }  //Dummy return value.  Remove when you implement!
        }
        return false;
    }

    @Override
    public int numNodes() {
        return numONodes;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public int numEdges() {
        return numOEdges;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public Map<String, Double> dijkstra(String start) {
        Map<String, Double> hmp = new HashMap<>();
        PriorityQueue<EdgeImpl> q = new PriorityQueue(numONodes, new Comparator<EdgeImpl>() {
            @Override
            public int compare(EdgeImpl i, EdgeImpl ii) {
                return Double.compare(i.getdist(), ii.getdist());
            }
        });
        hmp.put(start, 0.0);
        int cnt = 0;
        if (dgs.get(start) != null) {
            q.addAll(dgs.get(start));
        }
        EdgeImpl tmp;

        while (cnt < numONodes && !q.isEmpty()) {
            tmp = q.poll();
            String dest = tmp.getdest();
            if (nodes.containsKey(dest)) {
                if (!hmp.containsKey(dest)) {
                    hmp.put(dest, tmp.getdist());
                    cnt++;
                    if (dgs.get(dest) != null) {
                        for (EdgeImpl dg : dgs.get(dest)) {
                            dg.changedist(tmp.getdist());
                            q.add(dg);
                        }
                    }

                }
            }
            ;  //Dummy return value.  Remove when you implement!
        }
        return hmp;
    }
}