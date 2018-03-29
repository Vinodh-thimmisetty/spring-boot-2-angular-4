package com.vinodh.springbootangularmongodbcrudoperations.repository;


import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongoConfig;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

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
