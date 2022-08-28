package com.spotify.test.pages;

import com.spotify.test.driver.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import com.spotify.test.methods.Methods;

public class HomePage extends Driver {

    Methods methods;

    By createPlaylistButtonLocator = By.xpath("//button[@data-testid = 'create-playlist-button']");
    By playlistTextLocator = By.xpath("//h2[text() = 'Çalma listesi']");
    By selectedPlaylistLocator = By.xpath("//h1[contains(@class, 'Type__TypeElement')][@dir = 'auto']");
    By playlistEditDetailsTextLocator = By.xpath("//h1[text() = 'Edit details']");
    By playlistNameInputLocator = By.xpath("//input[@data-testid = 'playlist-edit-details-name-input']");
    By playlistDetailsSaveButtonLocator = By.xpath("//span[text() = 'Kaydet']");
    By addedPlaylistLocator = By.xpath("//ul[@role = 'list']//a[1]");
    By searchButtonLocator = By.xpath("//nav[@aria-label = 'Giriş']//div[1]//ul[1]//li[2]");
    By searchInputAreaLocator = By.xpath("//input[@placeholder = 'Ne dinlemek istiyorsun?']");
    By songsButtonLocator = By.xpath("//button[@role = 'checkbox']//span[text() = 'Şarkılar']");
    By songsAreaTitleTextLocator = By.xpath("//span[text() = 'başlık']");
    By songsAreaThirdSongTextLocator = By.xpath("//span[text() = '3']");
    By popupMenuAddPlaylistButtonLocator = By.xpath("//span[text() = 'Çalma listesine ekle']");
    By popupMenuRemoveSongButtonLocator = By.xpath("//span[text() = 'Bu çalma listesinden kaldır']");
    By secondSongLocator = By.xpath("//span[text() = '2']/parent::div");
    By stopButtonLocator = By.xpath("//div[@data-testid = 'action-bar-row']//button[@data-testid = 'play-button']");
    By moreButtonLocator = By.xpath("//div[@data-testid = 'action-bar-row']//button[@data-testid = 'more-button']");
    By deletePlaylistButtonLocator = By.xpath("//span[text() = 'Sil']");
    By popupDeletePlaylistButtonLocator = By.xpath("//span[text() = 'SİL']");


    public HomePage(){

        methods = new Methods();
    }

    // Check the positive login process
    public void loginPositiveControl(String username){

        String usernameElement = "figure[data-testid = \'user-widget-avatar\']" + "[title = \"" + username + "\"]";
        Assertions.assertTrue(methods.isElementVisible(By.cssSelector(usernameElement),20));

        String usernameTitle = methods.getAttribute(By.cssSelector("figure[data-testid = \'user-widget-avatar\']"), "title");
        Assertions.assertEquals(username,usernameTitle);
    }

    // Create a playlist
    public void createPlaylist(){
        Assertions.assertTrue(methods.isElementVisible(createPlaylistButtonLocator,20));
        methods.clickElement(createPlaylistButtonLocator);
    }

    // Change the playlist name to "Spotify Listem" and check the playlist's name
    public void changePlaylistName(String playlistName){

        Assertions.assertTrue(methods.isElementVisible(playlistTextLocator,20));
        Assertions.assertTrue(methods.isElementVisible(selectedPlaylistLocator,20));
        methods.clickElement(selectedPlaylistLocator);

        Assertions.assertTrue(methods.isElementVisible(playlistEditDetailsTextLocator,20));
        Assertions.assertTrue(methods.isElementVisible(playlistNameInputLocator,20));
        Assertions.assertTrue(methods.isElementClickable(playlistDetailsSaveButtonLocator,20));

        methods.sendKeys(playlistNameInputLocator,playlistName);
        methods.clickElement(playlistDetailsSaveButtonLocator);
        methods.waitBySeconds(2);

        Assertions.assertTrue(methods.isElementVisible(addedPlaylistLocator,20));
        Assertions.assertEquals(methods.getText(addedPlaylistLocator),"Spotify Listem");
    }

    // Searching songs that related with "Daft Punk"
    public void searchSongs(String songName){

        Assertions.assertTrue(methods.isElementVisible(searchButtonLocator,20));
        methods.clickElement(searchButtonLocator);

        Assertions.assertTrue(methods.isElementClickable(searchInputAreaLocator,20));
        methods.sendKeys(searchInputAreaLocator,songName);

        Assertions.assertTrue(methods.isElementVisible(songsButtonLocator,20));
        methods.clickElementJs(songsButtonLocator);

        Assertions.assertTrue(methods.isElementVisible(songsAreaTitleTextLocator,20));
        Assertions.assertTrue(methods.isElementVisible(songsAreaThirdSongTextLocator,20));

    }

    // Adding songs to the created playlist
    public void addSongsToPlaylist(int startIndex, int finishIndex, String playlistName){

        // First element (song) index is 2.
        for (int i = startIndex; i <= finishIndex; i++){

            String hoverSong = "//div[@aria-rowindex = \"" + i + "\"]";
            Assertions.assertTrue(methods.isElementClickable(By.xpath(hoverSong),20));
            methods.hoverElement(By.xpath(hoverSong));

            String popupMenuElement = "//div[@aria-rowindex = \"" + i + "\"]//button[@aria-haspopup = \'menu\']";
            Assertions.assertTrue(methods.isElementClickable(By.xpath(popupMenuElement),20));

            methods.clickElementJs(By.xpath(popupMenuElement));
            Assertions.assertTrue(methods.isElementClickable(popupMenuAddPlaylistButtonLocator,20));
            methods.hoverElement(popupMenuAddPlaylistButtonLocator);

            String playlistElement = "//div[@data-tippy-root]//div//li//button//span[contains(text(), \"" + playlistName  + "\")]";
            Assertions.assertTrue(methods.isElementVisible(By.xpath(playlistElement),20));
            methods.hoverElement(By.xpath(playlistElement));
            methods.clickElement(By.xpath(playlistElement));


        }

    }

    // Play a song and wait 10 seconds
    public void playSong(String playlistName){

        String leftBarCreatedPlaylist = "//li[@data-testid = 'rootlist-item']//a//span[contains(text(), \"" + playlistName + "\")]";
        Assertions.assertTrue(methods.isElementClickable(By.xpath(leftBarCreatedPlaylist),20));
        methods.clickElementJs(By.xpath(leftBarCreatedPlaylist));

        Assertions.assertTrue(methods.isElementClickable(secondSongLocator,20));
        methods.hoverElement(secondSongLocator);
        methods.clickElement(secondSongLocator);

        methods.waitBySeconds(10);
    }

    // Stop the song
    public void stopSong(){

        Assertions.assertTrue(methods.isElementVisible(stopButtonLocator,20));
        methods.clickElement(stopButtonLocator);

    }

    // Remove a song
    public void removeSong(int songNumber){

        String hoverSong = "//div[@aria-rowindex = \"" + songNumber +"\"]";
        methods.scrollElementIfNeeded(By.xpath(hoverSong));
        Assertions.assertTrue(methods.isElementClickable(By.xpath(hoverSong),20));
        methods.hoverElement(By.xpath(hoverSong));

        String popupMenuElement = "//div[@aria-rowindex = \"" + songNumber + "\"]//button[@aria-haspopup = \'menu\']";
        Assertions.assertTrue(methods.isElementClickable(By.xpath(popupMenuElement),20));

        methods.clickElementJs(By.xpath(popupMenuElement));
        Assertions.assertTrue(methods.isElementClickable(popupMenuRemoveSongButtonLocator,20));
        methods.hoverElement(popupMenuRemoveSongButtonLocator);
        methods.clickElement(popupMenuRemoveSongButtonLocator);

    }

    // Delete the playlist and check it
    public void deletePlaylist(String playlistName){

        Assertions.assertTrue(methods.isElementVisible(moreButtonLocator,20));
        methods.hoverElement(moreButtonLocator);
        methods.clickElementJs(moreButtonLocator);

        Assertions.assertTrue(methods.isElementClickable(deletePlaylistButtonLocator,20));
        methods.hoverElement(deletePlaylistButtonLocator);
        methods.clickElement(deletePlaylistButtonLocator);

        Assertions.assertTrue(methods.isElementClickable(popupDeletePlaylistButtonLocator,20));
        methods.clickElement(popupDeletePlaylistButtonLocator);
        String leftBarCreatedPlaylist = "//li[@data-testid = 'rootlist-item']//a//span[contains(text(), \"" + playlistName + "\")]";
        methods.waitBySeconds(2);
        Assertions.assertFalse(methods.isElementVisible(By.xpath(leftBarCreatedPlaylist),2));

    }

}
