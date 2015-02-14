var country = ""
val capitalOf = Map("Sweden" -> "Stockholm", "Denmark" -> "Copenhagen", "India" -> "New Dehli")
val question = Label("")
val answer = TextField("")
val text = Label("")
val checkButton = Button("ENTER") { checkAction }
var remaining = capitalOf.keySet
def pickCountry = { country = scala.util.Random.shuffle(remaining.toVector).head }
def removeCountry = { remaining = remaining - country }
def setQuestion = question.setText("What is the capital city of " + country + "?")
def resetAnswer = answer.setText("")
def drawGui = draw(PicShape.widget(ColPanel(question, answer, checkButton, text)))
def askNextCountry =  { pickCountry; setQuestion; resetAnswer } 
def checkAction { if (answer.getText == capitalOf(country)) correct else incorrect }
def correct = { text.setText("BRAVO!"); removeCountry; checkAgain  }
def checkAgain = if (remaining.isEmpty) quit else askNextCountry
def incorrect = text.setText("Nope! Hint: it begins with " + capitalOf(country).take(2))
def init = { cleari; drawGui; askNextCountry }
def quit = { cleari; setBackground(black); write("YOU CAUGHT THEM ALL!") }
toggleFullScreenCanvas; init
// *** Challenges (1) Add more pairs country -> capital  (2) Add points (3) Measure time 