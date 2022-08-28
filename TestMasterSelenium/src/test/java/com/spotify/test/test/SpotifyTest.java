package com.spotify.test.test;

import com.spotify.test.driver.Driver;
import com.spotify.test.pages.GuestPage;
import com.spotify.test.pages.HomePage;
import com.spotify.test.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SpotifyTest extends Driver {

    GuestPage guestPage;
    LoginPage loginPage;
    HomePage homePage;

    By loginPositiveUsernameLocator = By.xpath("//span[text() = 'RobotLama']");

    String playlistName = "Spotify Listem";
    String songName = "Daft Punk";

    //****************************************************************************

    // ----------------- Please enter your e-mail, password and username ----------------
    String email;
    String password;
    String userName;
    //****************************************************************************

    int songNumber = 4;

    private static final Logger logger = LogManager.getLogger(Driver.class);


    @Test
    public void spotifyPlaylistTest(){

        initialize();

        guestPage.navSite();

        String title = driver.getTitle();

        Assertions.assertEquals(title,"Spotify – Web Player");

        guestPage.loginButtonClicked();

        loginPage.loginPageControl();

        //****************************************************************************

        // ----------------- Please enter your e-mail, password and username ----------------
        loginPage.loginPositive(email,password);
        homePage.loginPositiveControl(userName);
        logger.error("--- Please enter your e-mail, password and username to SpotifyTest.java file ---");
        //****************************************************************************

        homePage.createPlaylist();

        homePage.changePlaylistName(playlistName);

        homePage.searchSongs(songName);

        // I applied for the 1st, 2nd and 3rd songs
        // First song start from index 2
        homePage.addSongsToPlaylist(2,4, playlistName);

        homePage.playSong(playlistName);

        homePage.stopSong();

        homePage.removeSong(songNumber);

        homePage.deletePlaylist(playlistName);
    }



    public void initialize(){
        guestPage = new GuestPage();
        loginPage = new LoginPage();
        homePage = new HomePage();
    }


}
