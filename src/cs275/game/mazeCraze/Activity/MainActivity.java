package cs275.game.mazeCraze.Activity;

import cs275.game.mazeCraze.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView(R.layout.main_menu);
		Button random = (Button) findViewById( R.id.btnrandom );
		random.setOnClickListener( this );
		Button create = (Button) findViewById( R.id.btncreate );
		create.setOnClickListener( this );
		Button load = (Button) findViewById( R.id.btnload );
		load.setOnClickListener( this );
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId() ) {
		case R.id.btnrandom:
			startActivity( new Intent( this, RandomActivity.class ) );
			break;
		case R.id.btncreate:
			startActivity( new Intent( this, CreatorOptionsActivity.class ) );
			break;
		case R.id.btnload:
			startActivity( new Intent( this, LoadActivity.class ) );
			break;
		}
	}
}
