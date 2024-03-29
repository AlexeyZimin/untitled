package pr1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Signature;
import java.util.Date;
import java.util.GregorianCalendar;


@Service
public class DBInitialiser {
    private Logger logger = LoggerFactory.getLogger(DBInitialiser.class);
    @Autowired SingerDao singerDao;
    @Autowired InstrumentDao instrumentDao;

    @PostConstruct
    public void initDb(){
        logger.info("Starting database initialization...");
        Instrument guitar = new Instrument();
        Instrument piano = new Instrument();
        piano.setInstrumentId("Piano");
        instrumentDao.save(piano);
        guitar.setInstrumentId("Guitar");
        instrumentDao.save(guitar);

        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date((new GregorianCalendar(1977,9,16)).getTime().getTime()));
        singer.addInstrument(guitar);
        singer.addInstrument(piano);

        Album album1=new Album();
        album1.setTitle("The Search For Everything");
        album1.setReleaseDate(new Date((new GregorianCalendar(2017,0,20)).getTime().getTime()));
        singer.addAlbum(album1);

        Album album2 = new Album();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(new Date((new GregorianCalendar(2009,10,17)).getTime().getTime()));
        singer.addAlbum(album2);
        singerDao.save(singer);
        logger.info("Database initialisation finished.");
    }
}
