var melody = [];
var melodyStates = [];
var states = [];
var headers = [];
var abcText;
var otherAbc;
var levels = 0;
var container = [];
var headersContainer = "";
var score = document.getElementById('score');
var svgNotes;
var play_this;
var stop_this;
var paper;
// per la modifica
var this_id;
var this_id_num;
var ok_thisId;
// parametri menu quando si clicca una nota
var edi;
var del;
var can;
// parametri menu edit
var edit_mod;
var edit_can;
var edit_ins;
var edit_text;

var globalCont = 0;

var variazione = 0;

function addState(state, state2) {
	states.push(state);
	melodyStates.push(melody);
	levels++;
}

function init() {

	$(".editNote_menu").hide();
	$(".click_menu").hide();

	edi = document.getElementById('edi_btn');
	del = document.getElementById('del_btn');
	can = document.getElementById('can_btn');

	edit_mod = document.getElementById('mod_btn');
	edit_can = document.getElementById('can_edi_btn');
	edit_ins = document.getElementById('ins_btn');
	edit_text = document.getElementById('editNote');

	paper = document.getElementById('paper0');

	play_this = document.getElementById('play');
	stop_this = document.getElementById('stop');

	abcText = document.getElementById('abc');
	otherAbc = document.getElementById("headers");

	headersContainer = otherAbc.value;
	addState(abcText.value);
	console.log("value", abcText);

}

/*
function autoQ() {
	console.log("asdasgadgasjfjn");
	var cont = 0;
	for (i = 0; i < melody.length; i++) {
		if (melody[i] !== "\n")
			cont++

		if (cont === 5) {
			melody.splice(i + 1, 0, "\n");
			abcText.value = abcText.value + "\n";
			cont = 0;
		}
	}
	
	
	
	
	//renderThisAbc();
}*/

function overWrite() {

	states.splice(levels, states.length);
	melody.splice(levels - 1, melody.length);
	console.log("numero di stati dopo overwrite:", states.length);
	console.log("numero di livelli dopo overwrite:", levels);

}

function addToMelody(arrayMelody) {
	melody = melody.concat(arrayMelody);
}

function reset() {
	// for (i = 0; i < states.length; i++)
	// undo();

	abcText.value = states[0];
	levels = 1;
	overWrite();

	ensembleAll();
}

function undo() {

	console.log("undo prima", levels);
	console.log("lunghezza", states.length);
	console.log("array melody undo", melody);

	if (levels > 1) {
		abcText.value = states[levels - 2];
		levels--;

	}

	

	console.log("undo dopo", levels);
	ensembleAll();
}

function redo() {
	melody = abcText.value.split("#");
	for (var i = melody.length - 1; i >= 0; i--) {
		if (melody[i] === "") {
			melody.splice(i, 1);
		}
	}
	console.log("array melody redo", melody);
	console.log("redo prima", levels);
	if (levels < states.length) {
		abcText.value = states[levels];
		levels++;
	}

	console.log("redo dopo", levels);
	ensembleAll();

}



function renderThisAbc() {

	console.log("melody array", melody);

	addState(abcText.value);

	console.log("push", abcText.value);
	// ABCJS.renderAbc("paper0", abcText.value);
	ensembleAll();

}

function pushNewFigure(musicalFigure) {

	overWrite();
	melody.push(musicalFigure);
	abcText.value = abcText.value + musicalFigure;
	renderThisAbc();
}

function length() {
	var length = document.getElementById("lengthSelector");
	var noteLenght = length.options[length.selectedIndex].value;
	return noteLenght;
}

function pitch() {
	var pitch = document.getElementById("pitchSelector");
	var notePitch = pitch.options[pitch.selectedIndex].value;

	var octave;

	switch (notePitch) {

	case "0":
		octave = ",,,,";
		break;

	case "1":
		octave = ",,,";
		break;

	case "2":
		octave = ",,";
		break;

	case "3":
		octave = ",";
		break;
	case "4":
		octave = "";
		break;

	case "5":
		octave = "'";
		break;
	case "6":
		octave = "''";
		break;
	case "7":
		octave = "'''";
		break;
	default:
		octave = "";
		break;

	}

	console.log("octave", octave);
	return octave;
}

function writeA() {

	pushNewFigure("#A" + pitch() + length() + "#");
	console.log("test");
}
function writeB() {
	pushNewFigure("#B" + pitch() + length() + "#");
}
function writeC() {
	pushNewFigure("#C" + pitch() + length() + "#");
}
function writeD() {
	pushNewFigure("#D" + pitch() + length() + "#");
}

function writeE() {
	pushNewFigure("#E" + pitch() + length() + "#");
}
function writeF() {
	pushNewFigure("#F" + pitch() + length() + "#");
}

function writeG() {
	pushNewFigure("#G" + pitch() + length() + "#");
}

function writeZ() {
	pushNewFigure("#z" + length() + "#");
}
/*
 * var symbol = ""; var melody_index = ""; var melody_lenght = "";
 * 
 * 
 * function insertSymbolLeft(symbol) { melody = abcText.value.split("#");
 * melody.splice(melody.length-2,0,symbol); }
 */


/*
function purgeMelody() {
	console.log("Purge this melody");
	console.log("array melody in purge ", JSON.stringify(melody));

	for (i = 0; i < melody.length; i++)
		console.log("elemento di melody", melody[i], "\n");
}*/

function writeChord() {
	if (document.getElementById("chord").value == "ON") {

		// elimino i # che si trovano fra l'inizio e la fine dell'accordo
		melody = abcText.value.split("#");
		abcText.value = melody.join("");

		pushNewFigure("] #");

		document.getElementById("chord").value = "OFF";
		return;
	}

	if (document.getElementById("chord").value == "OFF") {
		pushNewFigure("# [");
		document.getElementById("chord").value = "ON";
		return;
	}

}

function insertSymbolLeft(symbol) {

	var testString = "";
	var testArray = [];
	testString = melody.join("");
	testArray = testString.split("#");

	console.log("testArray", testArray);

	melody = abcText.value.split("#");

	// console.log("melody", melody)

	melody.splice(melody.length - 2, 0, symbol);
	melody.push("#");
	abcText.value = melody.join("");
	renderThisAbc();

	// console.log("melody", melody)

}
function sharp() {
	if (levels > 1)
		insertSymbolLeft("#^");
}

function flat() {
	if (levels > 1)
		insertSymbolLeft("#_");
}

function natural() {
	if (levels > 1)
		insertSymbolLeft("#=");
}

function doubleSharp() {
	if (levels > 1)
		insertSymbolLeft("#^^");
}

function doubleFlat() {
	if (levels > 1)
		insertSymbolLeft("#__");
}

// chiavi
function writeAltoClef() {
	pushNewFigure("#[K:clef=alto]#");
}

function wirteTrebleClef() {
	pushNewFigure("#[K:clef=treble]#");
}

function wirteBassClef() {
	pushNewFigure("#[K:clef=bass]#");
}

// BARS
function writeClosingStartBar() {
	pushNewFigure("#[| #");
}

function writeClosingEndBar() {
	pushNewFigure("#|] #");
}

function writeSingleBar() {
	pushNewFigure("#| #");

}

function writeDoubleBar() {
	pushNewFigure("#|| #");
}

function writeRepeatStart() {
	pushNewFigure("#[|: #");
}

function writeRepeatEnd() {
	pushNewFigure("#:|] #");
}

function RepeatEndStart() {
	pushNewFigure("#:: #");
}

function writeTie() {
	pushNewFigure("#- #");
}

// Segni di espressione

function writeFFFF() {
	pushNewFigure("#!ffff! #");
}

function writeFFF() {
	pushNewFigure("#!fff! #");
}

function writeFF() {
	pushNewFigure("#!ff! #");
}

function writeFORTE() {
	pushNewFigure("#!f! #");
}

function writeMF() {
	pushNewFigure("#!mf! #");
}

function writeMP() {
	pushNewFigure("#!mp! #");
}

function writeP() {
	pushNewFigure("#!p! #");
}

function writePP() {
	pushNewFigure("#!pp! #");
}

function writePPP() {
	pushNewFigure("#!ppp! #");
}

function writePPPP() {
	pushNewFigure("#!pppp! #");
}

function writeSF() {
	pushNewFigure("#!sfz! #");
}

function endOfTheLine() {
	pushNewFigure("\n");
}

// informazioni aggiuntive compositore ecc ecc

function properties_apply() {
	headers = [ "T:" + document.getElementById('title').value + "\n",
			"C:" + document.getElementById('composer').value + "\n",
			"K:" + document.getElementById('keySignature').value + "\n",
			"M:" + document.getElementById('timeSignature').value + "\n",
			"L:" + document.getElementById('noteUnit').value + "\n",
			"Q:" + document.getElementById('tempo').value + "\n" ];

	headersContainer = headers.join("");
	// mergeHeaders(headersContainer);
	ensembleAll();
	// renderThisAbc();

	/*
	 * var string = [ abcText.value ]; var strong = string.join("");
	 * 
	 * strong = strong.replace(headers[0], "T:"CIPPOLIPPO");
	 * console.log(strong);
	 */
}

var x1, y1;

function ensembleAll() {

	var mParams = '{ "parserParams":[{"print":false, "header_only":false, "stop_on_warning":false}],'
			+ ' "engraverParams":[{"scale":1, "staffwidth":740, "paddingtop":15, "paddingbottom":30,'
			+ ' "paddingright":50, "paddingleft":15, "editable":true, "add_classes":true }]}';

	param = JSON.parse(mParams);
	//autoQ();
	otherAbc.value = headersContainer + abcText.value;

	// array di tipo tune object da passare come parametro alla funzione di
	// animazione del cursore
	// il rendering della partitura è eseguito automaticamente con i parametri
	// fornjiti nell'array mparams
	var goABC = ABCJS.renderAbc("paper0", otherAbc.value,
			param.parserParams[0], {}, {
				startingTune : 0
			});

	$("rect").remove();
	// ogni volta che faccio l'ensemble di tutto riassegno gli id a tutte le
	// note presenti in partitura e rimuovo i rect di svg

	// var battuta = document.getElementsByClassName("m");

	svgNotes = $("path:not(.none, .staff, .staff-extra, .symbol, .beam-elem)"); // seleziono
																				// tutti
																				// i
																				// figli
																				// di
																				// svg
																				// tranne
																				// elementi
																				// nulli,
																				// chiavi
																				// varie,
																				// simbolo
																				// della
																				// time
																				// signature
																				// ecc
																				// ecc
	// svgNotes = document.getElementsByClassName("note");
	var contatoreAccapo = 0;
/*
	for (var i = melody.length - 1; i >= 0; i--) {
		if (melody[i] === "\n") {
			melody.splice(i, 1);
		}
	}
*/
	console.log("array melody maledetto", melody);

	for (var i = 0; i < svgNotes.length; i++) {
		svgNotes[i].setAttribute("id", "nota" + i);
		svgNotes[i].style.cursor = "pointer";

		// gestione menu note per la modifica
		svgNotes[i].onclick = function() {

			
			var this_elem = this;

			this_id = this.id;
			this_id_num = this_id.replace("nota", "");
			ok_thisId = parseInt(this_id_num);
			console.log("nota numero", ok_thisId);
			console.log("this id num", this_id_num);
			
			console.log("sono quel figlio di puttana di i:",i);
			
			var text = $("#nota" + (ok_thisId)).attr("class");
			var regex = new RegExp(/l\d+/);
			var delta = parseInt(regex.exec(text).toString().substring(1));
			
			
			console.log("ho clickato una cazzo di nota evviva");

			$(".editNote_menu").hide();

			var x = event.clientX;
			var y = event.clientY;
			x1 = x;
			y1 = y;

			console.log("coordinate:", x, y);

			$(".click_menu").show();

			$(".click_menu").css("top", (y + 50) + "px");
			$(".click_menu").css("left", (x - 75) + "px");

	

			del.onclick = function() {

				$(".click_menu").hide();
				console.log("sono il delta con this_id_num",this_id_num);
				console.log("sono il delta con delta",delta);
				console.log("sono il delta con delta",this_id_num + delta);
				
				
				melody.splice(ok_thisId + delta, 1);
				abcText.value = melody.join("#");
				renderThisAbc();

			}

			can.onclick = function() {
				$(".click_menu").hide();
			}

			edi.onclick = function() {
				$(".click_menu").hide();
				$(".editNote_menu").show();

				$(".editNote_menu").css("top", (y1 + 50) + "px");
				$(".editNote_menu").css("left", (x1 - 75) + "px");

				edit_can.onclick = function() {
					$(".editNote_menu").hide();
				}

				edit_mod.onclick = function() {
					overWrite();
					$(".editNote_menu").hide();
					
					console.log("mod.dete",delta);
					melody[ok_thisId+delta] = edit_text.value;
					abcText.value = melody.join("#");
					console.log("valore editText Modify", edit_text.value);
					renderThisAbc();
				}

				edit_ins.onclick = function() {
					overWrite();
					$(".editNote_menu").hide();
					melody.splice(ok_thisId + 1, 0, edit_text.value);
					abcText.value = melody.join("#");
					console.log("valore editText Insert", edit_text.value);
					renderThisAbc();

				}

			}

		};

	}

	// ///////////////////////////////////
	play_this.onclick = function() {
		ABCJS.renderPlayMidi(midi, otherAbc.value, {}, {}, {});
		$(".midi > a").css("display", "none");
		var midlink = $('.midi > a').attr('href');
		MIDIjs.play(midlink);
		window.ABCJS.startAnimation(paper, goABC[0], {
			"hideFinishedMeasures" : false,
			"showCursor" : true
		});

		stop_this.onclick = function() {
			MIDIjs.stop(midlink);
			window.ABCJS.stopAnimation();
		};
	};

}

function save() {
	var txt_file = new Blob([ headersContainer + "--" + abcText.value ], {
		type : 'text/plain'
	});
	var txt_name = $("#title").val();
	var downloadFile = document.createElement("a");
	downloadFile.download = txt_name;
	downloadFile.innerHTML = "Download File";
	downloadFile.href = window.URL.createObjectURL(txt_file);
	downloadFile.click();
}

function load() {
	var fileLoaded = document.getElementById('load').files[0];
	var reader = new FileReader();
	reader.onload = function(event) {
		var text = event.target.result.toString();
		var text1 = text.split("--");

		console.log("AAAAAAAA", text1[0]);
		console.log("BBBBBBBB", text1[1]);

		headersContainer = text1[0];
		abcText.value = text1[1];
		console.log("testooooo", otherAbc.value)
		ensembleAll();
	}
	reader.readAsText(fileLoaded, "UTF-8");

}
