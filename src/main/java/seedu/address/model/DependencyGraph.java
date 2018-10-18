package seedu.address.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.GraphCycleException;

/**
 * DependencyGraph models the dependencies of the tasks so that checks such as cyclic dependencies can be performed and
 * useful information such as the topological sort of the dependencies can be determined.
 */
public class DependencyGraph {
    private Map<String, Set<String>> adjacencyList = new HashMap<>();

    public DependencyGraph(List<Task> taskList) {
        for (Task task: taskList) {
            String hash = Integer.toString(task.hashCode());
            Set<String> edges = task.getDependency().getHashes();
            adjacencyList.put(hash, edges);
        }
        if (checkPresenceOfCycle()) {
            throw new GraphCycleException();
        }
    }

    /**
     * Updates graph and returns true if the update Task will result in a cycle in the graph
     */
    public boolean checkCyclicDependency(Task updatedTask) {
        String hash = Integer.toString(updatedTask.hashCode());
        adjacencyList.remove(hash);
        adjacencyList.put(hash, updatedTask.getDependency().getHashes());
        return checkPresenceOfCycle();
    }

    /**
     * Checks if there will be a cycle in the graph
     */
    public boolean checkPresenceOfCycle() {
        Set<String> unvisited = new HashSet<>(adjacencyList.keySet());
        Set<String> stack = new HashSet<>();
        List<String> visited = new ArrayList<>();
        for (String node: adjacencyList.keySet()) {
            if (unvisited.contains(node)) {
                if (depthFirstSearch(node, unvisited, visited, stack, adjacencyList)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns topological sort of the graph
     * @return list of hashes of tasks sorted by topological order
     */
    public List<String> topologicalSort() {
        Set<String> unvisited = new HashSet<>(adjacencyList.keySet());
        Set<String> stack = new HashSet<>();
        List<String> visited = new ArrayList<>();
        for (String node: adjacencyList.keySet()) {
            if (unvisited.contains(node)) {
                if (depthFirstSearch(node, unvisited, visited, stack, adjacencyList)) {
                    throw new GraphCycleException();
                }
            }
        }
        return visited;
    }

    /**
     * Performs dfs on graph to check for cycles
     * @param node next node to check
     * @param unvisited set of unvisited nodes
     * @param stack set of nodes in current path. Used to check cycles
     * @param adjacencyList
     * @return true is there is a cycle
     */
    private boolean depthFirstSearch(String node, Set<String> unvisited, List<String> visited, Set<String> stack,
                                     Map<String, Set<String>> adjacencyList) {
        if (stack.contains(node)) {
            return true;
        }
        unvisited.remove(node);
        stack.add(node);
        Set<String> edges = adjacencyList.getOrDefault(node, new HashSet<String>());
        for (String nextNode: edges) {
            if (depthFirstSearch(nextNode, unvisited, visited, stack, adjacencyList)) {
                return true;
            }
        }
        if (!visited.contains(node)) {
            visited.add(node);
        }
        stack.remove(node);
        return false;
    }

}