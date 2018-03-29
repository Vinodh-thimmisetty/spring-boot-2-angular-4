package com.vinodh.springbootangularmongodbcrudoperations.repository;


import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongoConfig;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class)
// @DataMongoTest
@Ignore
public class ContactRepositoryTest {

    @Autowired
    ContactRepository contactRepository;

    private static MongodExecutable mongodExecutable;

    @Before
    public void setup() throws Exception {

        // Server Instance

        MongodStarter testStarter = MongodStarter.getDefaultInstance();
        IMongoConfig mongoConfig = new MongodConfigBuilder().version(Version.Main.DEVELOPMENT).net(new Net(27017, false)).build();

        mongodExecutable = testStarter.prepare((IMongodConfig) mongoConfig);
        mongodExecutable.start();


    }

    @After
    public void cleanup() {
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }

    @Test
    public void getContactInfo(){
            Assert.assertFalse(contactRepository.findById("abcd1234").isPresent());
    }

}
