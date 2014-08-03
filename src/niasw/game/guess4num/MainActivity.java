/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package niasw.game.guess4num;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.KeyEvent;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.method.ScrollingMovementMethod;
import android.util.Log; //DEBUG

/**
 * Android Guess4num
 * by niasw<niasw@pku.edu.cn>
 * @author niasw(Sun Smallwhite)
 */
public class MainActivity extends Activity {
  public TextView txtWlcm = null;
  public TextView txtScrn = null;
  public Button bttnClr = null;
  public Button bttnGss = null;
  public EditText edtNums = null;
  public Toast tst = null;
  public GuessNumberGame gm = null;
  public boolean initFlag = true; // whether to clean txtScrn
  /** Called when the activity is first created. */
  @Override
    public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.main);
      txtWlcm = (TextView)findViewById(R.id.txtWlcm);
      txtScrn = (TextView)findViewById(R.id.txtScrn);
      bttnClr = (Button)findViewById(R.id.bttnClr);
      bttnGss = (Button)findViewById(R.id.bttnGss);
      edtNums = (EditText)findViewById(R.id.edtNums);
      txtScrn.setMovementMethod(ScrollingMovementMethod.getInstance());
      bttnClr.setOnClickListener(bttnListener);
      bttnGss.setOnClickListener(bttnListener);
      edtNums.setOnKeyListener(edtKeyListener);
      if (icicle==null) {
        gm = new GuessNumberGame();
      } else {
        txtWlcm.setText(icicle.getString("txtWlcm"));
        txtScrn.setText(icicle.getString("txtScrn"));
        edtNums.setText(icicle.getString("edtNums"));
        gm = new GuessNumberGame(icicle.getInt("gm.num"));
        initFlag = icicle.getBoolean("initFlag");
      }
    }

  @Override
    protected void onSaveInstanceState(Bundle icicle) {
      super.onSaveInstanceState(icicle);
      icicle.putString("txtWlcm",txtWlcm.getText().toString());
      icicle.putString("txtScrn",txtScrn.getText().toString());
      icicle.putString("edtNums",edtNums.getText().toString());
      icicle.putInt("gm.num",gm.getNum());
      icicle.putBoolean("initFlag",initFlag);
    }

  private OnClickListener bttnListener = new OnClickListener() {
    @Override
      public void onClick (View vw) {
        Button bttn = (Button)vw;
        switch (bttn.getId()) {
          case R.id.bttnClr:
            MainActivity.this.edtNums.setText("");
            break;
          case R.id.bttnGss:
            String _gesstr = MainActivity.this.edtNums.getText().toString();
            int _ges;
            try {
              _ges = Integer.parseInt(_gesstr);
              int _rep = MainActivity.this.gm.check(_ges);
              String _repstr = MainActivity.this.gm.parse(_ges,_rep);
              if (MainActivity.this.initFlag) {
                MainActivity.this.txtScrn.setText(_repstr+"\n");
                MainActivity.this.initFlag=false;
              } else {
                MainActivity.this.txtScrn.setText(MainActivity.this.txtScrn.getText()+_repstr+"\n");
              }
              if (_rep==400) {
                MainActivity.this.tst = Toast.makeText(getApplicationContext(),R.string.winMssg, Toast.LENGTH_LONG); 
                MainActivity.this.tst.setGravity(Gravity.CENTER, 0, 0); 
                MainActivity.this.tst.show(); 
                MainActivity.this.txtWlcm.setText(R.string.winMssg);
              } else {
                MainActivity.this.tst = Toast.makeText(getApplicationContext(),_repstr, Toast.LENGTH_LONG); 
                MainActivity.this.tst.setGravity(Gravity.CENTER, 0, 0); 
                MainActivity.this.tst.show(); 
              }
              MainActivity.this.edtNums.setText("");
            } catch (NumberFormatException e) {
              MainActivity.this.tst = Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG); 
              MainActivity.this.tst.setGravity(Gravity.CENTER, 0, 0); 
              MainActivity.this.tst.show(); 
            } catch (Exception e) {
              MainActivity.this.tst = Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG); 
              MainActivity.this.tst.setGravity(Gravity.CENTER, 0, 0); 
              MainActivity.this.tst.show(); 
            }
            break;
          default:
            break;
        }
      }
  };

  private OnKeyListener edtKeyListener = new OnKeyListener() {
    @Override
      public boolean onKey(View vw, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
          String _gesstr = MainActivity.this.edtNums.getText().toString();
          int _ges;
          try {
            _ges = Integer.parseInt(_gesstr);
            int _rep = MainActivity.this.gm.check(_ges);
            String _repstr = MainActivity.this.gm.parse(_ges,_rep);
            if (MainActivity.this.initFlag) {
              MainActivity.this.txtScrn.setText(_repstr+"\n");
              MainActivity.this.initFlag=false;
            } else {
              MainActivity.this.txtScrn.setText(MainActivity.this.txtScrn.getText()+_repstr+"\n");
            }
            if (_rep==400) {
              MainActivity.this.tst = Toast.makeText(getApplicationContext(),R.string.winMssg, Toast.LENGTH_LONG); 
              MainActivity.this.tst.setGravity(Gravity.CENTER, 0, 0); 
              MainActivity.this.tst.show(); 
              MainActivity.this.txtWlcm.setText(R.string.winMssg);
            } else {
              MainActivity.this.tst = Toast.makeText(getApplicationContext(),_repstr, Toast.LENGTH_LONG); 
              MainActivity.this.tst.setGravity(Gravity.CENTER, 0, 0); 
              MainActivity.this.tst.show(); 
            }
            MainActivity.this.edtNums.setText("");
          } catch (NumberFormatException e) {
            MainActivity.this.tst = Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG); 
            MainActivity.this.tst.setGravity(Gravity.CENTER, 0, 0); 
            MainActivity.this.tst.show(); 
          } catch (Exception e) {
            MainActivity.this.tst = Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG); 
            MainActivity.this.tst.setGravity(Gravity.CENTER, 0, 0); 
            MainActivity.this.tst.show(); 
          }
        }
        return false;
      }
  };
}
