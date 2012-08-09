import net.hellonico.freetss.*;

FreeTSSLibrary tts;

void setup() {
    tts = new FreeTSSLibrary(this);
}  

void draw() {
  
}

void mousePressed() {
    tts.speak("Bonjour Miwa");
}