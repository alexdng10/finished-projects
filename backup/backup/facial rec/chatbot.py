import cohere
from cohere.responses.classify import Example

co = cohere.Client('997s4Pljdbus7nZZaeVCWFMzcnIaDTUkV6ZrFyW2')

examples1 = [
    Example("I am sad", "happyspec"),
    Example("I am happy", "happy"),
    Example("I am angry", "angry"),
    Example("I am unhappy", "happyspec"),
    Example("I am excited", "happy"),
    Example("I am upset", "angry"),
    Example("cheer me up", "happyspec"),
    Example("I'm feeling good", "happy"),
    Example("I'm not happy", "angry"),
    Example("It's been rough", "happyspec"),
    Example("I've been good", "happy"),
    Example("I am bothered", "angry"),
    Example("Show me a sad photo", "sad"),
    Example("Show me a happy photo", "happy"),
    Example("Show me a angry photo", "angry"),
    Example("Remind me of a sad moment", "sad"),
    Example("Remind me of a happy moment", "happy"),
    Example("Remind me of a moment where I was angry", "angry"),
    Example("When was I sad?", "sad"),
    Example("When was I happy?", "happy"),
    Example("When was I angry?", "angry"),
    Example("Throwback to a depressing time", "sad"),
    Example("Throwback to a joyous time", "happy"),
    Example("Throwback to an annoying time", "angry"),
    Example("I am scared", "fear"),
    Example("I am neutral", "neutral"),
    Example("I am surprised", "surprise"),
    Example("I am fearful", "fear"),
    Example("I am okay", "neutral"),
    Example("That was unexpected", "surprise"),
    Example("Terrify me", "fear"),
    Example("I'm feeling fine", "neutral"),
    Example("I was not anticipating that", "surprise"),
    Example("That was scary", "fear"),
    Example("I've been doing alright", "neutral"),
    Example("My heart is racing", "surprise"),
    Example("Show me a horror photo", "fear"),
    Example("Show me a neutral photo", "neutral"),
    Example("Show me a surprising photo", "surprise"),
    Example("Remind me of a scary moment", "fear"),
    Example("Remind me of a neutral moment", "neutral"),
    Example("Remind me of a moment where I was surprised", "surprise"),
    Example("When was I afraid?", "fear"),
    Example("When was I neutral?", "neutral"),
    Example("When was I surprised?", "surprise"),
    Example("Throwback to a horrifying time", "fear"),
    Example("Throwback to a mediocre time", "neutral"),
    Example("Throwback to an out of the ordinary time", "surprise"),
    Example("I feel horrible", "happyspec"),
    Example("I feel like crap", "happyspec"),
    Example("I'm gloomy", "happyspec"),
    Example("I had a bad day", "happyspec"),
    Example("It has been miserable", "happyspec"),
    Example("I am heartbroken", "happyspec"),
    Example("I feel pessimistic", "happyspec"),
    Example("I feel emotionless", "neutral"),
    Example("I feel bad", "happyspec"),
    Example("My lowest moment", "sad"),
    Example("My greatest moment", "happy"),
    Example("I'm hurt", "happyspec"),
    Example("I am crying", "happyspec"),
]

examples2 = [
    Example("Show me a selfie", "individual"),
    Example("Show me a group photo", "group"),
    Example("Show me a happy photo of myself", "individual"),
    Example("Show me a good moment with friends", "group"),
    Example("A photo of me", "individual"),
    Example("A photo with my family", "group"),
    Example("Show me a time when I was sad", "individual"),
    Example("Show me a time where my friends and I were unhappy", "group"),
    Example("A time when I was alone", "individual"),
    Example("A time when I was with others", "group"),
    Example("When was I by myself?", "individual"),
    Example("When was I with people?", "group"),
    Example("A lonely photo", "individual"),
    Example("An unforgettable moment with friends", "group"),
    Example("Me independent", "individual"),
    Example("A class of students", "group"),
    Example("My greatest moment", "individual"),
    Example("My lowest moment", "individual"),

]

def intent(text_input):
    inputs = [text_input]
    response1 = co.classify(
        inputs=inputs,
        examples=examples1,
    )
    if float(response1[0].confidences[0]) < 0.9:
        mood = "nonsense"
    else:
        mood = response1[0].predictions[0]

    response2 = co.classify(
        inputs=inputs,
        examples=examples2,
    )
    if float(response2[0].confidences[0]) < 0.9:
        setting = "nonsense"
    else:
        setting = response2[0].predictions[0]

    if mood == "happyspec":
        result = "happy group"
    else:
        result = mood + " " + setting
    return result
