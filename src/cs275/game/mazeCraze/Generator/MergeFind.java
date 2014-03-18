package cs275.game.mazeCraze.Generator;

class MergeFind<E> {
	// This is an implementation of the standard Merge Find abstract data structure //
	// It also includes path compression in it's find method for increased efficiency //
	private E data;
	private MergeFind<E> parent;
	private int rank;

	public MergeFind(E x) {
		data = x;
		parent = this;
		rank = 0;
	}

	public void merge(MergeFind<E> other) {
		MergeFind<E> thisroot = find();
		MergeFind<E> otherroot = other.find();
		if ( thisroot.equals( otherroot ) )
			return;
		if ( thisroot.rank < otherroot.rank )
			thisroot.parent = otherroot;
		else if ( thisroot.rank > otherroot.rank )
			otherroot.parent = thisroot;
		else {
			otherroot.parent = thisroot;
			thisroot.rank++;
		}
	}

	public MergeFind<E> find() {
		if ( parent != this )
			parent = parent.find();
		return parent;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		MergeFind<E> other = (MergeFind<E>) o;
		return data.equals( other.data );
	}
}