using System;
using System.Collections.Generic;

class Program {
  public class Node {
    public Node(string name)
    {
        Name = name;
        _neighbors = new HashSet<Node>();
        _isBig = name.Equals(name.ToUpper());
    }

    private HashSet<Node> _neighbors;
    public void addNeighbor(Node n) 
    {
      if(!_neighbors.Contains(n)) {
        _neighbors.Add(n);
      }
    }

    public HashSet<Node> getNeighbors() {
      return _neighbors;
    }

    private string _name;  
    public string Name    
    {
        get => _name;
        set => _name = value;
    }

    private bool _isBig;  
    public bool isBig    
    {
        get => _isBig;
        set => _isBig = value;
    }
  }

  public static int traverse(HashSet<Node> visited, Node current, bool duped) {
    if(current.Name == "end") {
      return 1;
    }
    int result = 0;
    if(!current.isBig) {
      visited.Add(current);
    }
    foreach(Node n in current.getNeighbors()) {
      if(n.isBig || !visited.Contains(n)) {
        result += traverse(new HashSet<Node>(visited), n, duped);
      }
      if(!duped && "start" != n.Name && visited.Contains(n)) {
        result += traverse(new HashSet<Node>(visited), n, true);
      }
    }
    return result;
  }

  public static void Main (string[] args) {
    string[] lines = System.IO.File.ReadAllLines(@"day12.txt");
    HashSet<Node> caves = new HashSet<Node>();
    Queue<Node> worklist = new Queue<Node>();
    Node start = null;

    foreach (string line in lines)
    {
      string pre = line.Split("-")[0];
      string post = line.Split("-")[1];
      Node first = null;
      Node second = null;

      foreach (Node n in caves) {
        if(n.Name == pre) {
          first = n;
        } else if(n.Name == post) {
          second = n;
        } 
      }
      if(first == null) {
        first = new Node(pre);
        caves.Add(first);
      }
      if(second == null) {
        second = new Node(post);
        caves.Add(second);
      }

      first.addNeighbor(second);
      second.addNeighbor(first);

      if(start == null) {
        if(pre.Equals("start")) {
          start = first;
        } else if(post.Equals("start")) {
          start = second;
        }
      }
    }
    
    Console.WriteLine(traverse(new HashSet<Node>(), start, false));
  }
}
