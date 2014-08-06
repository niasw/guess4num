/**
 * Android Guess4num
 * by niasw<niasw@pku.edu.cn>
 * @author niasw(Sun Smallwhite)
 */
package niasw.game.guess4num;

import java.util.Random;

public class GuessNumberGame{
  int num; //The number
  int ges; //The guess
  int rpt=0; //The report
  Random randseed=new Random();

  public GuessNumberGame() {
    num=randseed.nextInt(9999);
  }
  public GuessNumberGame(int setNum) {
    if (setNum<0) {setNum=-setNum;}
    if (setNum>9999) {setNum=setNum%10000;}
    num=setNum;
  }
  public int getNum() {
    return num;
  }
  public static String parse(int _ges, int _rep) {
    /**
     * generate reply message from integer return from Method "check".
     * _ges: guess number.
     * _rep: reply from check Method.
     */
    String ret = new String("");
    ret=ret+String.format("%04d",_ges)+"  ";
    ret=ret+(_rep/100)+"A"+(_rep%100)+"B";
    return ret;
  }
  public int check(int _ges) {
    return check(_ges,num);
  }
  public static int check(int _ges, int _num) {
    /**
     * check how many digits are in the right place, and how many digits are included.
     * _ges: guess number.
     * _num: target number.
     */
    int include=0;
    int perfect=0;
    char cges[]=new char[4];
    char cnum[]=new char[4];
    for (int it=0;it<4;++it) {
      cges[it]=(char)(_ges%10);
      cnum[it]=(char)(_num%10);
      _ges=_ges/10;
      _num=_num/10;
    }
    for (int it=0;it<4;it++) {
      if (cges[it]==cnum[it]) {
        perfect++;cges[it]='-';cnum[it]='-';
      }
    }
    for (int it2=0;it2<4;it2++) {
      for (int it1=0;it1<4;it1++) {
        if (cges[it1]==cnum[it2]&&cnum[it2]!='-') {
            include++;cges[it1]='-';cnum[it2]='-';
            assert(it1!=it2):"What? Leakage detected from Perfect Match Scanning!";
        }
      }
    }
    return perfect*100+include;
  }
}
