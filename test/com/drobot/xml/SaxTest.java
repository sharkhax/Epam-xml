package test.com.drobot.xml;

import com.drobot.xml.builder.DefaultPath;
import com.drobot.xml.builder.SaxBuilder;
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

public class SaxTest {

    @Test
    public void paperParseTest() {
        try {
            SaxBuilder<Paper> builder = new SaxBuilder<>(new PaperHandler());
            builder.buildPapersSet(DefaultPath.NEWSPAPERS_XML);
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
            SaxBuilder<Magazine> builder = new SaxBuilder<>(new MagazineHandler());
            builder.buildPapersSet(DefaultPath.NEWSPAPERS_XML);
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
            SaxBuilder<Booklet> builder = new SaxBuilder<>(new BookletHandler());
            builder.buildPapersSet(DefaultPath.NEWSPAPERS_XML);
            Set<Booklet> result = builder.getPapers();
            for (Booklet booklet : result) {
                System.out.println(booklet);
            }
        } catch (ReaderException e) {
            Assert.fail();
        }
    }
}
