package cs275.game.mazeCraze;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class MazeGenerator {
	TreeSet<Vec> visited = new TreeSet<Vec>();
	//ArrayDeque<Edge> stack = new ArrayDeque<Edge>();
	ArrayList<Edge> path = new ArrayList<Edge>();
	int _sizex, _sizey;
	Random rand = new Random(System.currentTimeMillis());
	public MazeGenerator(int sizex, int sizey)
	{
		_sizex = sizex;
		_sizey = sizey;
	}
	public ArrayList<Boolean> getMaze()
	{
		ArrayList<Boolean> ret = new ArrayList<Boolean>();
		for(int y = 0; y < _sizey; y++)
			for(int x = 0; x < _sizex; x++)
			{
				if(y%2==0 && x%2==0)
					ret.add(true);
				else if(y%2==1 && x%2==1)
					ret.add(false);
				else
					ret.add(path.contains(new Edge(new Vec(x/2,y/2),new Vec((x+1)/2,(y+1)/2))));
			}
		return ret;
	}
	protected class Edge
	{
		@Override
		public boolean equals(Object o) {
			if(o instanceof Edge)
			{
				Edge other = (Edge) o;
				return (other._from.equals(_from) && other._to.equals(_to)) || (other._from.equals(_to) && other._to.equals(_from));
			}
			else if(o instanceof Vec)
			{
				Vec other = (Vec) o;
				return (_to.equals(other));
			}
			else
				return false;
		}
		Vec _from, _to;
		public Edge(Vec from, Vec to) {
			_from = from;
			_to = to;
		}
		public String toString()
		{
			return _from+"->"+_to;
		}
	}
	protected class Vec implements Comparable<Vec>
	{
		@Override
		public boolean equals(Object o) {
			if(o instanceof Edge)
			{
				Edge other = (Edge) o;
				return equals(other._to);
			}
			else if(o instanceof Vec)
			{
				Vec other = (Vec) o;
				return (other._x == _x && other._y == _y);
			}
			else
				return false;
		}
		public ArrayList<Edge> getPossible() {
			ArrayList<Edge> possible = new ArrayList<Edge>();
			if(_x+1 < (_sizex+1)/2)
				possible.add(new Edge(this,new Vec(_x+1,_y)));
			if(_y+1 < (_sizey+1)/2)
				possible.add(new Edge(this,new Vec(_x,_y+1)));
			if((_x-1) >= 0)
				possible.add(new Edge(this,new Vec(_x-1,_y)));
			if((_y-1) >= 0)
				possible.add(new Edge(this,new Vec(_x,_y-1)));
			return possible;
		}
		int _x, _y;
		public Vec(int x, int y) {
			_x = x;
			_y = y;
		}
		public String toString()
		{
			return "("+_x+","+_y+")";
		}
		@Override
		public int compareTo(Vec o) {
			if(o._x > _x)
				return 1;
			else if(o._x < _x)
				return -1;
			else
			{
				if(o._y > _y)
					return 1;
				else if(o._y < _y)
					return -1;
				else
					return 0;
			}
		}
	}
}
