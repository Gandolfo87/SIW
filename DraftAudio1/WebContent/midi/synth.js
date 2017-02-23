
//inizializzazione della tastiera virtuale
var keyboard = new QwertyHancock({
	id : 'keyboard',
	width : document.getElementById("quintaRiga").clientWidth,
	height : 150,
	octaves : 5,
});

// master Volume Controlli
var context = new AudioContext(), masterVolume = context.createGain(), oscillators = {};
masterVolume.gain.value = 0.8;
masterVolume.connect(context.destination);

// document.getElementById("F3").style.backgroundColor = "green";
// $("#F3").css("background-color","green");


// variabili da correggere
var melodyArray= [] ;
var timingArray= [] ;
var noteNameArray= [];
var canTranscribe = false;
var currentTimeInput;
var minNoteDuration = 1;
var lastTimeInput;

// lo definirà l'utente questo parametro
var tempo = $("#metronomo").val();
// console.log("Tempo value is:",tempo);


var canWritePause = true;


// listener up e down di qwerty hancock
keyboard.keyDown = function(note, frequency) {
	canWritePause = false;
	var sourceTime = new Date();
	currentTimeInput = sourceTime.getTime();
// console.log("premuto con tastiera", note)
	playNote(frequency);	
};

keyboard.keyUp = function(note, frequency) {	
	
	var sourceTime2 = new Date();
	lastTimeInput = sourceTime2.getTime();
	lastTimeInput = lastTimeInput - currentTimeInput;
	// console.log("last", lastTimeInput);
	oscillators[frequency].stop(context.currentTime);
	oscillators[frequency].disconnect();
	// (1000/(Math.round(tempo/60)))
	minNoteDuration = parseInt (minNoteDuration +  ((tempo/60) * (lastTimeInput / 1000)));
	// console.log("duration",minNoteDuration);
	Transcribe(note,minNoteDuration);
	minNoteDuration = 1;
	
	canWritePause = true;

};

var context = new AudioContext(), oscillators = {};
var context = new AudioContext(), oscillators = {};


// da invocare ogni volta che cambio il metronomo
function setTempo(){
	tempo = $("#metronomo").val();
	// console.log("new Tempo value is:",tempo);
}


// MIDI

if (navigator.requestMIDIAccess) {
	navigator.requestMIDIAccess().then(success, failure);
}



// controllando se il flusso midi è presente
function success(midi) {
	var inputs = midi.inputs.values();
	// inputs is an Iterator
	for (var input = inputs.next(); input && !input.done; input = inputs.next()) {
		// ogni volta che ricevo un midiMessage chiamo la funzione onMIDIMessage
		input.value.onmidimessage = onMIDIMessage;
	}
}


// controllo flusso midi input output e fallimento
function failure() {
	console.error('No access to your midi devices.')
}

function onMIDIMessage(message) {
	
	// console.log("tipo di controllo", message.data[0], "nota",
	// message.data[1],"intensità", message.data[2]);
	var frequency = midiNoteToFrequency(message.data[1]);

	if (message.data[0] === 144 && message.data[2] > 0) {
		var sourceTime = new Date();
		currentTimeInput = sourceTime.getTime();
		playNote(frequency);
		document.getElementById(midi_map[message.data[1]]).style.backgroundColor = "grey";
	}

	if (message.data[0] === 128 || message.data[2] === 0) {
		var tipo = document.getElementById(midi_map[message.data[1]]).getAttribute('data-note-type');
		stopNote(frequency);
		
		// TRASCRIZIONE NORMALE
// Transcribe(midi_map[message.data[1]]);
		var sourceTime2 = new Date();
		lastTimeInput = sourceTime2.getTime();
		lastTimeInput = lastTimeInput - currentTimeInput;
		// console.log("last", lastTimeInput);
		
		
		
		minNoteDuration = parseInt (minNoteDuration +  ((tempo/60) * (lastTimeInput / 1000)));
	// console.log("duration",minNoteDuration);
		
		var midiNote = midi_map[message.data[1]];
		
		Transcribe(midiNote,minNoteDuration);
		minNoteDuration = 1;
		

		if (tipo == 'black')
			document.getElementById(midi_map[message.data[1]]).style.backgroundColor = "black";
		else if (tipo == 'white')
			document.getElementById(midi_map[message.data[1]]).style.backgroundColor = "white";

	}
}

function midiNoteToFrequency(note) {
	return Math.pow(2, ((note - 69) / 12)) * 440;
}

// web audio api oscillatori e conversione segnale a suono
function playNote(frequency) {
	oscillators[frequency] = context.createOscillator();
	oscillators[frequency].type = "square";
	oscillators[frequency].frequency.value = frequency;
	oscillators[frequency].connect(context.destination);
	oscillators[frequency].start(context.currentTime);

}


// fermo il suono generato dall'oscillatore alla frequenza indicata
function stopNote(frequency) {
	oscillators[frequency].stop(context.currentTime);
	oscillators[frequency].disconnect();
}

// funzione che genera una pausa sulla text area indicata
function writePause (){
	
// if ( beat === measure){
// beat = 0;
// melodyArray.push("|");
// column++;
//	 
// if ( column === 4){
//			
// melodyArray.push("\n");
// column = 0;
// }
// }

	
	if ( canWritePause && canTranscribe){
// console.log("writing a pause");
	melodyArray.push("z1"); 
	timingArray.push(1);
	noteNameArray.push("z");
	
	
	}
}



var measure ;

// calcolo l'unita di tempo del brano

function calculateSignature(){
	var signature = $('#timeSignature').val();
// console.log("sign",signature);
	var res = parseInt(signature.substr(0, 1));
	measure = res;
// console.log("global",measure);
}




// controllo metronomo e trascrizione

var beat = 0;
var column = 0;

function playMetronomeSound(){
	var snd = new Audio('mSound.mp3');
	snd.volume = 1.0;
    snd.play();
    beat++;

 // console.log ("beats:",beat);
 // console.log ("column:",column);
    
    writePause();
    
	// console.log("time array", timingArray);
// console.log("nomi array", noteNameArray);
    
 }

// update del metronomo da raffinare
var updateMetronome;
var rec = (document.getElementById("rec"));
var recXs = (document.getElementById("recxs"));

function startTranscribe() {
	
	if(rec.value=="ON" || recXs.value=="ON" ){
		  stopTranscribe();
		  rec.value="OFF";
		  recXs.value="OFF";
		  return;
	  }
	
if(rec.value=="OFF" || recXs.value=="OFF"){
			canTranscribe = true;
			updateMetronome = setInterval(playMetronomeSound, (1000/(tempo/60))); 
			rec.value="ON";
			recXs.value="ON";
			 return;
	}	
}


// arresto la trascrizione e pulisco i due array di supporto che uso

function stopTranscribe() {

	clearTimeout(updateMetronome);
	canTranscribe = false;
// console.log("transcribe",canTranscribe);
	renderSheet(melodyArray,timingArray);
	melodyArray = [];
	timingArray = [];
}




// funzione che regola la trascrizione su text area
function Transcribe(note , duration) {
	if (canTranscribe) {
	// inserire pezzo come contenitore per l'output
		melodyArray.push(transcription_map[note] + duration);
		timingArray.push(duration);
		noteNameArray.push(transcription_map[note]);
		
		var tune = transcription_map[note];
		// console.log("Sto Trascrivendo una nota:", tune);
		
	}
}


// to do usare il join per castare un array in string senza virgole

function renderSheet (melodyArray,timingArray){
	 var v = document.getElementById('abc');
	
	 var melodyString = "";
	 var stato = v.value;
	 
	 quantize (melodyArray,timingArray);

	 for (i = 0 ; i<melodyArray.length;i++){
		 overWrite();
		 stato = stato + String(melodyArray[i]);
		// console.log("stato",stato);
		 	addState(stato);
	 }
	 
// console.log("string",melodyString);
	 
	 v.value = v.value + melodyArray.join("");
	// ABCJS.renderAbc("paper0", v.value);
// addState(v.value);
	 
	 addToMelody(melodyArray);
	 ensembleAll();

	
}

var battuta = 0;
var numTotBattute = 0 ;


//quantizzazione della partitura appena registrata

function quantize (melodyArray , timingArray){
	
	for (j = 0 ; j<timingArray.length ; j++){
		
		if (timingArray[j]!= "|" &&timingArray[j]!= "\n" )
		battuta += timingArray[j];


// if ( battuta + timingArray[j+1] > measure && timingArray[j]!= "|"
// &&timingArray[j]!= "\n" && noteNameArray[j+1]!= "z" )
// {
// var scarto = timingArray[j+1] -battuta ;
// var scarto2 = timingArray[j+1] + battuta - measure;
//			
// var nuovoNome = noteNameArray[j+1];
// var nuovaNota = nuovoNome+scarto;
//			
// console.log("nuovaNota",nuovaNota);
//			
// var notaBattutaAncoraDopo = nuovoNome + scarto2;
//			
// console.log("notaDopo",notaBattutaAncoraDopo);
//			
// melodyArray[j+1] = nuovaNota;
// timingArray[j+1] = scarto;
// noteNameArray[j+1] = nuovoNome;
//			
// melodyArray.splice(j+2,0,"|");
// timingArray.splice(j+2,0,"|");
// noteNameArray.splice(j+2,0,"cazzo");
//			
// melodyArray.splice(j+3,0,notaBattutaAncoraDopo);
// timingArray.splice(j+3,0,notaBattutaAncoraDopo);
// noteNameArray.splice(j+3,0,notaBattutaAncoraDopo);
//			
//			
// }
		// console.log("battuta",battuta);
		// se la battuta è piena
		if ( battuta === measure ){
			melodyArray.splice(j+1,0,"|");
			timingArray.splice(j+1,0,"|");
			// console.log(melodyArray);
			// console.log(timingArray);
			battuta = 0;
			numTotBattute ++;
		}
		
		if (numTotBattute === 4){
			melodyArray.splice(j+1,0,"\n");
			timingArray.splice(j+1,0,"\n");
			numTotBattute = 0;
		}
		
	}
}


//mappa per tutti i segnali midi di tipo "keyboard"
var midi_map = {


		21 : 'A0',
		22 : 'A#0',
		23 : 'B0',	
	
		24 : 'C1',
		25 : 'C#1',
		26 : 'D1',
		27 : 'D#1',
		28 : 'E1',
		29 : 'F1',
		30 : 'F#1',
		31 : 'G1',
		32 : 'G#1',
		33 : 'A1',
		34 : 'A#1',
		35 : 'B1',	
		
		36 : 'C2',
		37 : 'C#2',
		38 : 'D2',
		39 : 'D#2',
		40 : 'E2',
		41 : 'F2',
		42 : 'F#2',
		43 : 'G2',
		44 : 'G#2',
		45 : 'A2',
		46 : 'A#2',
		47 : 'B2',	
		
		
	48 : 'C3',
	49 : 'C#3',
	50 : 'D3',
	51 : 'D#3',
	52 : 'E3',
	53 : 'F3',
	54 : 'F#3',
	55 : 'G3',
	56 : 'G#3',
	57 : 'A3',
	58 : 'A#3',
	59 : 'B3',

	60 : 'C4',
	61 : 'C#4',
	62 : 'D4',
	63 : 'D#4',
	64 : 'E4',
	65 : 'F4',
	66 : 'F#4',
	67 : 'G4',
	68 : 'G#4',
	69 : 'A4',
	70 : 'A#4',
	71 : 'B4',

	72 : 'C5',
	73 : 'C#5',
	74 : 'D5',
	75 : 'D#5',
	76 : 'E5',
	77 : 'F5',
	78 : 'F#5',
	79 : 'G5',
	80 : 'G#5',
	81 : 'A5',
	82 : 'A#5',
	83 : 'B5',
	84 : 'C6',

	85 : 'C#6',
	86 : 'D6',
	87 : 'D#6',
	88 : 'E6',
	89 : 'F6',
	90 : 'F#6',
	91 : 'G6',
	92 : 'G#6',
	93 : 'A6',
	94 : 'A#6',
	95 : 'B6',
	96 : 'C7',
	
	97 : 'C#7',
	98: 'D7',
	99: 'D#7',
	10:'E7',
	101:'F7',
	102:'F#7',
	103:'G7',
	104:'G#7',
	105:'A7',
	106:'A#7',
	107:'B7',
	108:'C8',
	

};

//mappa per tutti i segnali "abc Notation" da convertire
var transcription_map = {

	// tasti bianchi
	C1 : "#C,,",
	C2 : "#C,",
	C3 : "#C",
	C4 : "#C'",
	C5 : "#C''",
	C6 : "#C'''",
	C7 : "#C''''",
	C8 : "#C'''''",

	D2 : "#D,",
	D3 : "#D",
	D4 : "#D'",
	D5 : "#D''",
	D6 : "#D'''",
	D7 : "#D''''",
	D8 : "#D'''''",

	E2 : "#E,",
	E3 : "#E",
	E4 : "#E'",
	E5 : "#E''",
	E6 : "#E'''",
	E7 : "#E''''",
	E8 : "#E'''''",

	F2 : "#F,",
	F3 : "#F",
	F4 : "#F'",
	F5 : "#F''",
	F6 : "#F'''",
	F7 : "#F''''",
	F8 : "#F'''''",

	G2 : "#G,",
	G3 : "#G",
	G4 : "#G'",
	G5 : "#G''",
	G6 : "#G'''",
	G7 : "#G''''",
	G8 : "#G'''''",

	A1 : "#A,,",
	A2 : "#A,",
	A3 : "#A",
	A4 : "#A'",
	A5 : "#A''",
	A6 : "#A'''",
	A7 : "#A''''",
	A8 : "#A'''''",

	B1 : "#B,,",
	B2 : "#B,",
	B3 : "#B",
	B4 : "#B'",
	B5 : "#B''",
	B6 : "#B'''",
	B7 : "#B''''",
	B8 : "#B'''''",

	// tasti neri

	'C#1' : "#^C,,",
	'C#2' : "#^C,",
	'C#3' : "#^C",
	'C#4' : "#^C'",
	'C#5' : "#^C''",
	'C#6' : "#^C'''",
	'C#7' : "#^C''''",
	'C#8' : "#^C'''''",

	'D#2' : "#^D,",
	'D#3' : "#^D",
	'D#4' : "#^D'",
	'D#5' : "#^D''",
	'D#6' : "#^D'''",
	'D#7' : "#^D''''",
	'D#8' : "#^D'''''",

	'E#2' : "#E,",
	'E#3' : "#E",
	'E#4' : "#E'",
	'E#5' : "#E''",
	'E#6' : "#E'''",
	'E#7' : "#E''''",
	'E#8' : "#E'''''",

	'F#2' : "#^F,",
	'F#3' : "#^F",
	'F#4' : "#^F'",
	'F#5' : "#^F''",
	'F#6' : "#^F'''",
	'F#7' : "#^F''''",
	'F#8' : "#^F'''''",

	'G#2' : "#^G,",
	'G#3' : "#^G",
	'G#4' : "#^G'",
	'G#5' : "#^G''",
	'G#6' : "#^G'''",
	'G#7' : "#^G''''",
	'G#8' : "#^G'''''",

	'A#1' : "#^A,,",
	'A#2' : "#^A,",
	'A#3' : "#^A",
	'A#4' : "#^A'",
	'A#5' : "#^A''",
	'A#6' : "#^A'''",
	'A#7' : "#^A''''",
	'A#8' : "#^A'''''",

	'B#2' : "#B,",
	'B#3' : "#B",
	'B#4' : "#B'",
	'B#5' : "#B''",
	'B#6' : "#B'''",
	'B#7' : "#B''''",
	'B#8' : "#B'''''",
};
