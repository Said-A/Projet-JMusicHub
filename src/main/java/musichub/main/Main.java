<<<<<<< HEAD:src/main/java/musichub/main/Main.java
package main.java.musichub.main;
import main.java.model.*;
=======
package musichub.main;
import model.*;
>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java

import java.util.*;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;

import javax.sound.sampled.*;

<<<<<<< HEAD:src/main/java/musichub/main/Main.java
import main.java.controlleur.*;
=======
import controller.*;
>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java

import java.io.File;
	
public class Main
{
 	public static void main (String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
 		

		MusicHub theHub = new MusicHub ();
		
		System.out.println("Type h for available commands");
		
		List<String> ls = new ArrayList<String>();
		List<AudioElement> lsAe = new ArrayList<AudioElement>();

		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();
		
		String albumTitle = null;
		String play = null;
		String playl =null;
		String elementToAdd=null;

<<<<<<< HEAD:src/main/java/musichub/main/Main.java
		if (choice.length() == 0) System.exit(0);	
		LoggerForMusic log = new LoggerForMusic();

		log.LogInfo("Lancement de l'app V1.2");					
=======
		if (choice.length() == 0) System.exit(0);						
>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java
		
		while (choice.charAt(0)!= 'q') 	{
			switch (choice.charAt(0)) 	{
				case 'h':
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 't':
					//album titles, ordered by date
					System.out.println(theHub.getAlbumsTitlesSortedByDate());
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'g':
					//songs of an album, sorted by genre
					System.out.println("Songs of an album sorted by genre will be displayed; enter the album name, available albums are:");
					System.out.println(theHub.getAlbumsTitlesSortedByDate());
					log.LogInfo("Album sorted by genre");		
					albumTitle = scan.nextLine();
					try {
						System.out.println(theHub.getAlbumSongsSortedByGenre(albumTitle));
					} catch (NoAlbumFoundException ex) {
						System.out.println("No album found with the requested title " + ex.getMessage());
					}
					GetContentForPlay(theHub);
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'd':
					//songs of an album
					System.out.println("Songs of an album will be displayed; enter the album name, available albums are:");
					System.out.println(theHub.getAlbumsTitlesSortedByDate());
					log.LogInfo("song of an Album");		
					albumTitle = scan.nextLine();
					try {
						System.out.println(theHub.getAlbumSongs(albumTitle));
					} catch (NoAlbumFoundException ex) {
						System.out.println("No album found with the requested title " + ex.getMessage());
					}
					GetContentForPlay(theHub);
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'u':
					//audiobooks ordered by author
					log.LogInfo("Audiobooks ordered by author");		
					System.out.println(theHub.getAudiobooksTitlesSortedByAuthor());
					GetContentForPlay(theHub);
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'c':
					// add a new song
					log.LogInfo("Add a new song");		
                    System.out.println("Enter a new song: ");
                    System.out.println("Song title: ");
                    String title = scan.nextLine();
                    System.out.println("Song genre (jazz, classic, hiphop, rock, pop, rap):");
                    String genre = scan.nextLine();
                    System.out.println("Song artist: ");
                    String artist = scan.nextLine();
                    System.out.println ("Song length in seconds: ");
                    int length = Integer.parseInt(scan.nextLine());
                    System.out.println("Song content: "); 
                    String content = scan.nextLine();
                    Song s = new Song (title, artist, length, content, genre);
                    theHub.addElement(s);
                    System.out.println("New element list: ");
                    Iterator<AudioElement> it = theHub.elements();
                    while (it.hasNext()) System.out.println(it.next().getTitle());
                    System.out.println("Song created!");
                    printAvailableCommands();
                    choice = scan.nextLine();
                break;
				case 'a':
					// add a new album
					log.LogInfo("Add a new album");		
                    System.out.println("Enter a new album: ");
                    System.out.println("Album title: ");
                    String aTitle = scan.nextLine();
                    System.out.println("Album artist: ");
                    String aArtist = scan.nextLine();
                    System.out.println ("Album length in seconds: ");
                    int aLength = Integer.parseInt(scan.nextLine());
                    System.out.println("Album date as YYYY-DD-MM: "); 
                    String aDate = scan.nextLine();
                    Album a = new Album(aTitle, aArtist, aLength, aDate);
                    theHub.addAlbum(a);
                    System.out.println("New list of albums: ");
                    Iterator<Album> ita = theHub.albums();
                    while (ita.hasNext()) System.out.println(ita.next().getTitle());
                    System.out.println("Album created!");
                    printAvailableCommands();
                    choice = scan.nextLine();
				break;
				case '+':
					//add a song to an album:
					log.LogInfo("Add a song to an album");		
					System.out.println("Add an existing song to an existing album");
					System.out.println("Type the name of the song you wish to add. Available songs: ");
					Iterator<AudioElement> itae = theHub.elements();
					while (itae.hasNext()) {
						AudioElement ae = itae.next();
						if ( ae instanceof Song) System.out.println(ae.getTitle());
					}
					String songTitle = scan.nextLine();	
					
					System.out.println("Type the name of the album you wish to enrich. Available albums: ");
					Iterator<Album> ait = theHub.albums();
					while (ait.hasNext()) {
						Album al = ait.next();
						System.out.println(al.getTitle());
					}
					String titleAlbum = scan.nextLine();
					try {
						theHub.addElementToAlbum(songTitle, titleAlbum);
					} catch (NoAlbumFoundException ex){
						System.out.println (ex.getMessage());
					} catch (NoElementFoundException ex){
						System.out.println (ex.getMessage());
					}
					System.out.println("Song added to the album!");
					printAvailableCommands();
                    choice = scan.nextLine();
				break;
				case 'l':
					// add a new audiobook
					log.LogInfo("Add audiobook ");
                    System.out.println("Enter a new audiobook: ");
                    System.out.println("AudioBook title: ");
                    String bTitle = scan.nextLine();
                    System.out.println("AudioBook category (youth, novel, theater, documentary, speech)");
                    String bCategory = scan.nextLine();
                    System.out.println("AudioBook artist: ");
                    String bArtist = scan.nextLine();
                    System.out.println ("AudioBook length in seconds: ");
                    int bLength = Integer.parseInt(scan.nextLine());
                    System.out.println("AudioBook content: "); 
                    String bContent = scan.nextLine();
                    System.out.println("AudioBook language (french, english, italian, spanish, german)");
                    String bLanguage = scan.nextLine();
                    AudioBook b = new AudioBook (bTitle, bArtist, bLength, bContent, bLanguage, bCategory);
                    theHub.addElement(b);
                    System.out.println("Audiobook created! New element list: ");
                    Iterator<AudioElement> itl = theHub.elements();
                    while (itl.hasNext()) System.out.println(itl.next().getTitle());
                    printAvailableCommands();
                    choice = scan.nextLine();
				break;
				case 'p':
					//create a new playlist from existing elements
					log.LogInfo("Create playlist");
					System.out.println("Add an existing song or audiobook to a new playlist");
					System.out.println("Existing playlists:");
					Iterator<PlayList> itpl = theHub.playlists();
					while (itpl.hasNext()) {
						PlayList pl = itpl.next();
						System.out.println(pl.getTitle());
					}
					System.out.println("Type the name of the playlist you wish to create:");
					String playListTitle = scan.nextLine();	
					PlayList pl = new PlayList(playListTitle);
					theHub.addPlaylist(pl);
					System.out.println("Available elements: ");
					
					Iterator<AudioElement> itael = theHub.elements();
					while (itael.hasNext()) {
						AudioElement ae = itael.next();
						System.out.println(ae.getTitle());
					}
					while (choice.charAt(0)!= 'n') 	{
						System.out.println("Type the name of the audio element you wish to add or 'n' to exit:");
						String elementTitle = scan.nextLine();	
                        try {
                            theHub.addElementToPlayList(elementTitle, playListTitle);
                        } catch (NoPlayListFoundException ex) {
                            System.out.println (ex.getMessage());
                        } catch (NoElementFoundException ex) {
                            System.out.println (ex.getMessage());
                        }
                            
						System.out.println("Type y to add a new one, n to end");
						choice = scan.nextLine();
					}
					System.out.println("Playlist created!");
					printAvailableCommands();
					choice = scan.nextLine();
					break;
				case '-':
					//delete a playlist
					log.LogInfo("Delete a playlist");
					System.out.println("Delete an existing playlist. Available playlists:");
					Iterator<PlayList> itp = theHub.playlists();
					while (itp.hasNext()) {
						PlayList p = itp.next();
						System.out.println(p.getTitle());
					}
					String plTitle = scan.nextLine();	
					try {
						theHub.deletePlayList(plTitle);
					}	catch (NoPlayListFoundException ex) {
						System.out.println (ex.getMessage());
					}
					System.out.println("Playlist deleted!");
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 's':
					//save elements, albums, playlists
					log.LogInfo("Save all");
					theHub.saveElements();
					theHub.saveAlbums();
					theHub.savePlayLists();
					System.out.println("Elements, albums and playlists saved!");
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				case 'e':
<<<<<<< HEAD:src/main/java/musichub/main/Main.java
					//display all songs
					log.LogInfo("Display all songs");
					System.out.println(theHub.GetAllSong());
					System.out.println("choose an Audio : ");
=======
					System.out.println(theHub.GetAllSong());
					System.out.println("choose an Audio : ");
					/*String choix = scan.nextLine();
					String cont = theHub.GetElementContent(choix);
					if (cont!= "")PlayMusic(cont);
					else System.out.println("Error the music doesn't exist");*/
>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java
					GetContentForPlay(theHub);
					printAvailableCommands();
					choice = scan.nextLine();
				break; 
				case 'o':
					//display all playlist
<<<<<<< HEAD:src/main/java/musichub/main/Main.java
					log.LogInfo("Display all playlist");
=======
>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java
					theHub.GetPlaylist();
					printAvailableCommands();
					choice = scan.nextLine();
				break; 
				case 'z':
					//display detail of a playlist
<<<<<<< HEAD:src/main/java/musichub/main/Main.java
					log.LogInfo("Display detail of a playlist");
=======
>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java
					System.out.println("List of playlist :");
					theHub.GetPlaylist();
					System.out.println("\nChoose a playlist :");
					play = scan.nextLine();
					try{
						System.out.println(theHub.GetElementInPlaylist(play));
					}catch(Exception e ){
						System.out.println("Error");
					}
					GetContentForPlay(theHub);
					printAvailableCommands();
					choice = scan.nextLine();
				break; 
				case 'r':
					//Search 
<<<<<<< HEAD:src/main/java/musichub/main/Main.java
					log.LogInfo("Search with string");
=======
>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java
					System.out.println("Search by name :");
					String sce = scan.nextLine();

					try{
						ls = theHub.GetAllBySearch(sce);
						if(ls.size() != 0)
							System.out.println(ls);
						lsAe = theHub.GetAllBySearchAE(sce);
						System.out.println(lsAe);
					}catch(Exception e ){
						System.out.println("Error");
					}
					GetContentForPlay(theHub);
					printAvailableCommands();
					choice = scan.nextLine();
				break; 
				case 'y' :
<<<<<<< HEAD:src/main/java/musichub/main/Main.java
					//add a song or audioBook to a playlist
					log.LogInfo("Add a song or audioBook to a playlist");
=======
>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java
					theHub.GetPlaylist();
					System.out.println("choose a playlist");
					playl = scan.nextLine();
					Iterator<AudioElement> ite = theHub.elements();
					while (ite.hasNext()) {
						AudioElement ae = ite.next();
						if ( ae instanceof Song) System.out.println(ae.getTitle());
					}
					
					while (choice.charAt(0)!= 'n') 	{
						System.out.println("Type the name of the audio element you wish to add or 'n' to exit:");
						elementToAdd = scan.nextLine();	
                        try {
                            theHub.addElementToPlayList(elementToAdd, playl);
                        } catch (NoPlayListFoundException ex) {
                            System.out.println (ex.getMessage());
                        } catch (NoElementFoundException ex) {
                            System.out.println (ex.getMessage());
                        }
    					System.out.println(elementToAdd +" added to " + playl);
						System.out.println("Type y to add a new one, n to end");
						choice = scan.nextLine();
					}
					System.out.println("To save this change wirte s");
					printAvailableCommands();
					choice = scan.nextLine();
				break;
				default:
<<<<<<< HEAD:src/main/java/musichub/main/Main.java
					log.LogInfo("Default");
=======
>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java
					System.out.println("\nVeuillez mettre une commande valable \n \n");
					printAvailableCommands();
					choice = scan.nextLine();
				break;
			}
		}
<<<<<<< HEAD:src/main/java/musichub/main/Main.java
		log.LogWarning("END");
=======
>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java
		System.out.println("Bye bye   :) ");
		scan.close();
	}

	private static void PlayMusic(String content) throws UnsupportedAudioFileException, IOException, LineUnavailableException{

		try{
			File file = new File(content);
	 		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
	 		Clip clip = AudioSystem.getClip();
	 		clip.open(audioStream);
	 		clip.start();
<<<<<<< HEAD:src/main/java/musichub/main/Main.java
	 		System.out.println("s: stop , p : play, q : quit");
=======
>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java

	 		Scanner scanMusic = new Scanner(System.in);
			String choix = scanMusic.nextLine();
	 		while(choix.charAt(0)!= 'q'){
	 			switch (choix.charAt(0)) 	{
	 				case 's':
	 					clip.stop();
	 					choix = scanMusic.nextLine();
	 				break;
	 				case 'p': 
	 					clip.start();
	 					choix = scanMusic.nextLine();
	 				break;
	 				default: 
	 					System.out.println("\nVeuillez mettre une commande valable \n \n");
	 					choix = scanMusic.nextLine();
	 				break;
	 			}
	 		}
	 		clip.close();
	 		System.out.println("Close the music");

 		}catch(Exception e){
 			System.out.println("This content doesn't exist");
 		}

	}

	private static void GetContentForPlay(MusicHub theHub) throws UnsupportedAudioFileException, IOException, LineUnavailableException{

		Scanner scanContent = new Scanner(System.in);
		String choix = scanContent.nextLine();
		String cont = theHub.GetElementContent(choix);

		if (cont!= "")PlayMusic(cont);
		else System.out.println("Error the music doesn't exist");
<<<<<<< HEAD:src/main/java/musichub/main/Main.java
=======


>>>>>>> 492d4d6a919a8ac0d1d39dc33d11c5cb0ad42313:src/musichub/main/Main.java
	}
	
	private static void printAvailableCommands() {
		System.out.println("t: display the album titles, ordered by date");
		System.out.println("g: display songs of an album, ordered by genre");
		System.out.println("d: display songs of an album");
		System.out.println("u: display audiobooks ordered by author");
		System.out.println("e: display all songs");
		System.out.println("o: display all playlist");
		System.out.println("r: Search :");
		System.out.println("z: display detail of a playlist");
		System.out.println("y: add a song or audioBook to a playlist");
		System.out.println("c: add a new song");
		System.out.println("a: add a new album");
		System.out.println("+: add a song to an album");
		System.out.println("l: add a new audiobook");
		System.out.println("p: create a new playlist from existing songs and audio books");
		System.out.println("-: delete an existing playlist");
		System.out.println("s: save elements, albums, playlists");
		System.out.println("q: quit program");
	}
}