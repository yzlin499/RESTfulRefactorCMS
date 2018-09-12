
interface SB{
    void pinA();
}

class caojiB implements SB{
    @Override
    public void pinA() {
        System.out.println("pinA");
    }
}

class putongB implements SB{

    @Override
    public void pinA() {
        System.out.println("putongA");
    }
}

class Proxy implements SB{

    SB sb;

    public Proxy(SB sb) {
        this.sb = sb;
    }

    @Override
    public void pinA() {
        try {
            System.out.println("zunbeiA");
            sb.pinA();
            System.out.println("Awanle");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


public class test {
    public static void main(String [] args){
        SB caojibin=new caojiB();

        SB dailibin=new Proxy(caojibin);
        ((Proxy) dailibin).sb=new putongB();

        dailibin.pinA();
    }
}
