import static org.fusesource.jansi.Ansi.ansi
import org.fusesource.jansi.Ansi.Color
import org.fusesource.jansi.Ansi.Attribute
import java.util.Random  

Random rand = new Random()  

int width = args.size()<1?80:args[0].toInteger()
int height = args.size()<2?25:args[1].toInteger()
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
	int yOffset
	sleep(sleepTime)
	ticks++
	letters.each{
	  it.y+=it.speed
	  if (it.y<=height&&it.y>0){
	  	  print ansi().cursor((int)(it.y),it.x).fgBright(Color.WHITE).a(Attribute.INTENSITY_BOLD_OFF).a(Attribute.INTENSITY_BOLD).a(characters[rand.nextInt(charactersSize)]).cursor(0,0)  
	  }
	  yOffset = 1
	  if (it.y-yOffset<=height&&it.y-yOffset>0){
	  	  print ansi().cursor((int)(it.y-yOffset),it.x).fgBright(Color.GREEN).a(Attribute.INTENSITY_BOLD_OFF).a(it.character).cursor(0,0)  	  
	  }
	  yOffset = it.speed*20
	  if (it.y-yOffset<=height&&it.y-yOffset>0){
	  	  print ansi().cursor((int)(it.y-yOffset),it.x).fgBright(Color.GREEN).a(Attribute.INTENSITY_BOLD_OFF).a(Attribute.INTENSITY_FAINT).a(it.character).cursor(0,0)  	  
	  }
	  yOffset = it.speed*40
	  if (it.y-yOffset<=height&&it.y-yOffset>0){
	  	  print ansi().cursor((int)(it.y-yOffset),it.x).fg(Color.GREEN).a(Attribute.INTENSITY_BOLD_OFF).a(Attribute.INTENSITY_FAINT).a(it.character).cursor(0,0)  	  
	  }
	  yOffset = it.speed*70
	  if (it.y-yOffset<=height&&it.y-yOffset>0){
	  	  print ansi().cursor((int)(it.y-yOffset),it.x).a(' ')	  
	  }
	}
	letters.removeAll{
	  it.y-it.speed*70>=height
	}
	if (ticks>2&&letters.size()<width/1.75){
	  ticks=0
	  letters.add(new Letter(x:rand.nextInt(width+1),y:0,character:characters[rand.nextInt(charactersSize)],speed:0.125d+rand.nextDouble()*0.25d))
	}
}