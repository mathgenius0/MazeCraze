package cs275.game.mazeCraze;

import java.util.ArrayList;

import android.util.Log;

public class DFSGen extends MazeGenerator {

	public DFSGen(int sizex, int sizey) {
		super(sizex, sizey);
		Edge curr = new Edge(null,new Vec(0,0));
		while(visited.size() < (_sizex+1)/2 * (_sizey+1)/2)
		{
			ArrayList<Edge> possible = curr._to.getPossible();
			while(!possible.isEmpty())
			{
				Edge temp = possible.remove(rand.nextInt(possible.size()));
				if(!visited.contains(temp._to))
				{
					curr = temp;
					visited.add(curr._to);
					path.add(curr);
					Log.v("gen",curr.toString());
					possible = curr._to.getPossible();
				}
			}
			//possible = curr._from.getPossible();
			curr = path.get(path.lastIndexOf(curr._from));
		}
	}

}
