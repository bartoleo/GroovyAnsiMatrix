import static org.fusesource.jansi.Ansi.ansi
import org.fusesource.jansi.Ansi.Color
import org.fusesource.jansi.Ansi.Attribute
import java.util.Random  

Random rand = new Random()  
 
int width = 80
int height = 25
def letters = []
int sleepTime = 15
int ticks=0

class Letter {
	int x
	double y
	double speed
	char character
}

def characters = ['ウ','ィ','リ','ア','ム','シ','ェ','イ','ル','ス','ピ','ア']
int charactersSize = characters.size() 

print ansi().cursor(1,1).reset().bg(Color.BLACK)

while (true){
	sleep(sleepTime)
	ticks++
	letters.each{
	  it.y+=it.speed
	  if (it.y<=height){
	  	  print ansi().cursor((int)(it.y),it.x).fgBright(Color.WHITE).a(Attribute.INTENSITY_BOLD_OFF).a(Attribute.INTENSITY_BOLD).a(characters[rand.nextInt(charactersSize)]).cursor(0,0)  
	  }
	  if (it.y-1<=height){
	  	  print ansi().cursor((int)(it.y-1),it.x).fgBright(Color.GREEN).a(Attribute.INTENSITY_BOLD_OFF).a(it.character).cursor(0,0)  	  
	  }
	  if (it.y-it.speed*20<=height){
	  	  print ansi().cursor((int)(it.y-it.speed*20),it.x).fgBright(Color.GREEN).a(Attribute.INTENSITY_BOLD_OFF).a(Attribute.INTENSITY_FAINT).a(it.character).cursor(0,0)  	  
	  }
	  if (it.y-it.speed*40<=height){
	  	  print ansi().cursor((int)(it.y-it.speed*40),it.x).fg(Color.GREEN).a(Attribute.INTENSITY_BOLD_OFF).a(Attribute.INTENSITY_FAINT).a(it.character).cursor(0,0)  	  
	  }
	  if (it.y-it.speed*60<=height){
	  	  print ansi().cursor((int)(it.y-it.speed*60),it.x).a(' ')	  
	  }
	}
	letters.removeAll{
	  it.y-it.speed*60>=height
	}
	if (letters.size()<width/1.5){
	  ticks=0
	  letters.add(new Letter(x:rand.nextInt(width+1),y:0,character:characters[rand.nextInt(charactersSize)],speed:0.125d+rand.nextDouble()*0.25d))
	}
}