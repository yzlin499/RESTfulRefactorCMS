import java.util.Calendar;
import java.util.Date;

public abstract class test {

    public static void main(String[] args) {
        Date date = new Date(1520179200000L);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        Calendar calendar1 = Calendar.getInstance();

        System.out.println(calendar1.get(Calendar.WEEK_OF_YEAR) - calendar.get(Calendar.WEEK_OF_YEAR));
    }

    public abstract void asd(String aasda);
}
