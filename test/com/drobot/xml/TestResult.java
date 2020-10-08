package test.com.drobot.xml;

import com.drobot.xml.entity.AbstractPaper;
import com.drobot.xml.entity.Booklet;
import com.drobot.xml.entity.Magazine;
import com.drobot.xml.entity.Paper;

import java.util.List;

public class TestResult {

    public static final List<Paper> PAPERS_TEST_RESULT;
    public static final List<Magazine> MAGAZINES_TEST_RESULT;
    public static final List<Booklet> BOOKLETS_TEST_RESULT;

    static {
        Paper paper1 =
                new Paper("gazetka", "2020-11-10", "Gazeta",
                        AbstractPaper.Type.NORMAL, false, false, 5, true);
        Paper paper2 =
                new Paper("paper2", "2020-12-22", "Breaking news",
                        AbstractPaper.Type.NORMAL, false, false, 2, false);
        Paper paper3 =
                new Paper("paper3", "2020-02-01", "Pravda",
                        AbstractPaper.Type.BRAND, true, true, 15, true);
        Paper paper4 =
                new Paper("paper4", "2020-10-25", "BBC",
                        AbstractPaper.Type.BRAND, true, true, 12, false);
        Paper paper5 =
                new Paper("paper5", "2019-01-01", "Happy new year",
                        AbstractPaper.Type.NORMAL, false, true, 5, false);
        PAPERS_TEST_RESULT = List.of(paper1, paper2, paper3, paper4, paper5);
        Magazine magazine1 =
                new Magazine("forbes", "2020-03-10", "Forbes",
                        AbstractPaper.Type.BRAND, true, true, 30, false, true);
        Magazine magazine2 =
                new Magazine("magazine2", "2020-01-02", "Kalambur",
                        AbstractPaper.Type.BRAND, true, true, 32, true, false);
        Magazine magazine3 =
                new Magazine("magazine3", "2020-02-10", "Playboy",
                        AbstractPaper.Type.BRAND, false, true, 28, false, true);
        Magazine magazine4 =
                new Magazine("magazine4", "2020-08-02", "Super fashion",
                        AbstractPaper.Type.FASHION, false, true, 52, false, true);
        Magazine magazine5 =
                new Magazine("magazine5", "2020-02-02", "For kids",
                        AbstractPaper.Type.NORMAL, false, false, 7, true, false);
        Magazine magazine6 =
                new Magazine("magazine6", "2020-02-29", "Some title",
                        AbstractPaper.Type.BRAND, true, true, 29, false, true);
        MAGAZINES_TEST_RESULT = List.of(magazine1, magazine2, magazine3, magazine4, magazine5, magazine6);
        Booklet booklet1 =
                new Booklet("booklet1", "2020-10-02", "Invitation",
                        AbstractPaper.Type.NORMAL, false, true, 1, true);
        Booklet booklet2 =
                new Booklet("booklet2", "2020-10-02", "Vacancy",
                        AbstractPaper.Type.NORMAL, false, true, 1, false);
        Booklet booklet3 =
                new Booklet("booklet3", "2020-10-02", "Advertisement",
                        AbstractPaper.Type.NORMAL, false, false, 1, false);
        Booklet booklet4 =
                new Booklet("booklet4", "2020-10-02", "Warning",
                        AbstractPaper.Type.NORMAL, false, false, 1, true);
        Booklet booklet5 =
                new Booklet("booklet5", "2020-10-02", "Manual",
                        AbstractPaper.Type.NORMAL, false, false, 1, false);
        BOOKLETS_TEST_RESULT = List.of(booklet1, booklet2, booklet3, booklet4, booklet5);
    }

    private TestResult() {
    }
}
