package br.ifpe.datastructures.abstractclass;

public abstract class BasicTree {
	
	private int size;
	private BasicNode rootNode;
	
	 public BasicTree() {
		 this.size = 0;
		 this.rootNode = null;
	}

	public int getSize() {
		return size;
	}

	public BasicNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(BasicNode rootNode) {
		this.rootNode = rootNode;
	}
}
