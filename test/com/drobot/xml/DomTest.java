package test.com.drobot.xml;

import com.drobot.xml.builder.DomBuilder;
import com.drobot.xml.entity.Booklet;
import com.drobot.xml.entity.Magazine;
import com.drobot.xml.entity.Paper;
import com.drobot.xml.exception.ReaderException;
import com.drobot.xml.handler.impl.BookletHandler;
import com.drobot.xml.handler.impl.MagazineHandler;
import com.drobot.xml.handler.impl.PaperHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class DomTest {

    @Test
    public void paperParseTest() {
        try {
        DomBuilder<Paper> builder = new DomBuilder<>(new PaperHandler());
            builder.buildPapersSet();
            Set<Paper> result = builder.getPapers();
            for (Paper paper : result) {
                System.out.println(paper);
            }
        } catch (ReaderException e) {
            Assert.fail();
        }
    }

    @Test
    public void magazineParseTest() {
        try {
            DomBuilder<Magazine> builder = new DomBuilder<>(new MagazineHandler());
            builder.buildPapersSet();
            Set<Magazine> result = builder.getPapers();
            for (Magazine magazine : result) {
                System.out.println(magazine);
            }
        } catch (ReaderException e) {
            Assert.fail();
        }
    }

    @Test
    public void bookletParseTest() {
        try {
            DomBuilder<Booklet> builder = new DomBuilder<>(new BookletHandler());
            builder.buildPapersSet();
            Set<Booklet> result = builder.getPapers();
            for (Booklet booklet : result) {
                System.out.println(booklet);
            }
        } catch (ReaderException e) {
            Assert.fail();
        }
    }
}
