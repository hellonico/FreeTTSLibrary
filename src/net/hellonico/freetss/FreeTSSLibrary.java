/**
 * FreeTTSLibrary
 * A collection of utilities for solving this and that problem.
 * http://yourlibraryname.com
 *
 * Copyright (C) 2012 Your Name http://yoururl.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      Your Name http://yoururl.com
 * @modified    07/25/2012
 * @version     0.1.1 (1)
 */

package net.hellonico.freetss;


import java.util.Locale;

import javax.speech.EngineCreate;
import javax.speech.EngineList;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

import processing.core.PApplet;

import com.sun.speech.freetts.jsapi.FreeTTSEngineCentral;

/**
 * This is a template class and can be used to start a new processing library or tool.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own library or tool naming convention.
 * 
 * @example Hello 
 * 
 * (the tag @example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 */

public class FreeTSSLibrary{
	
	PApplet myParent;
	Synthesizer synthesizer1;

	public FreeTSSLibrary(PApplet theParent) {
		myParent = theParent;
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void init() throws Exception {
		System.out.println("Init speech");
		Voice kevinHQ = new Voice("kevin16",  Voice.GENDER_DONT_CARE, Voice.AGE_DONT_CARE, null);
		SynthesizerModeDesc desc = new SynthesizerModeDesc(
				null,          // engine name
		                "general",     // mode name
		                Locale.US,     // locale
		                null,          // running
		                null);         // voice
		
//		synthesizer1 =
//                Central.createSynthesizer(generalDesc);
		
		FreeTTSEngineCentral central = new FreeTTSEngineCentral();
        EngineList list = central.createEngineList(desc); 
        
        if (list.size() > 0) { 
            EngineCreate creator = (EngineCreate) list.get(0); 
            synthesizer1 = (Synthesizer) creator.createEngine(); 
        } else {
        	throw new RuntimeException("cannot instanciate synthesizer");
        }
		
		synthesizer1.allocate();
		synthesizer1.getSynthesizerProperties().setVoice(kevinHQ);
		
//		synthesizer1.addSpeakableListener(
//			    new SpeakableAdapter() {
//				public void markerReached(SpeakableEvent e) {
//				    dumpEvent(e);
//				}
//				public void speakableCancelled(SpeakableEvent e) {
//				    dumpEvent(e);
//				}
//				public void speakableEnded(SpeakableEvent e) {
//				    dumpEvent(e);
//				}
//				public void speakablePaused(SpeakableEvent e) {
//				    dumpEvent(e);
//				}
//				public void speakableResumed(SpeakableEvent e) {
//				    dumpEvent(e);
//				}
//				public void speakableStarted(SpeakableEvent e) {
//				    dumpEvent(e);
//				}
//				public void topOfQueue(SpeakableEvent e) {
//				    dumpEvent(e);
//				}
//				public void wordStarted(SpeakableEvent e) {
//				    dumpEvent(e);
//				}
//				private void dumpEvent(SpeakableEvent e) {
//				    System.out.println(" EVT: " + e.paramString() + " source: " + e.getSource());
//				}
//			    });
		synthesizer1.resume();
	}
	
	public void speak(String text) {
		synthesizer1.speakPlainText(text, null);
	}
	
}

