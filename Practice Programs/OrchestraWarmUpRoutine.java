abstract class Instrument {
    public String play() {
        return "Strumming the strings";
    }
}

class StringInstrument extends Instrument {
    StringInstrument() {
        super();
    }

    @Override
    public String play() {
        return super.play();
    }
}

class Violin extends StringInstrument {
    Violin() {
        super();
    }

    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}

public class OrchestraWarmUpRoutine {
    public static void main(String[] args) {
        StringInstrument s = new StringInstrument();
        Violin v = new Violin();

        System.out.println(s.play());
        System.out.println(v.play());
    }
}