package com.redartedgames.ball.objects;

import java.util.List;

public class Node<T> {
    public T data;
    public Node<T> parent;
    public List<Node<T>> children;
}