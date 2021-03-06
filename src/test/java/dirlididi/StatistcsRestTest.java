package dirlididi;

import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;

import dirlididi.domain.Normal;
import dirlididi.repositories.NormalRepository;

@SpringApplicationConfiguration(classes = DirlididApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class StatistcsRestTest {
	
	@Value("${local.server.port}")
    private int port;
	
	
	private Normal usuarioNormal;
    
    @Autowired
    private NormalRepository normalRepository;
    
    @Before
    public void setUp(){
        usuarioNormal = new Normal("usuario.normal@gmail.com", "99999999");
        
        normalRepository.save(usuarioNormal);
        
    }

    @After
    public void tearDown() {
        normalRepository.deleteAll();
    }
    
    @Test
    public void testEstatisticasGlobais() {
    	given().when()
        .port(this.port)
        .get("/api/stats/global")
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK);
    }
    
    @Test
    public void testEstatisticasUsuarioNaoLogado() {
    	given().when()
        .port(this.port)
        .get("/api/stats/user")
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
    
    @Test
    public void testEstatisticasDoUsuario() {
    	RestAssured.authentication = basic("usuario.normal@gmail.com", "99999999");
    	given().when()
        .port(this.port)
        .get("/api/stats/user")
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK);
    }


}
