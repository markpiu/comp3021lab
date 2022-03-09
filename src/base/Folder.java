package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>{
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (Note note : notes){
			if (note instanceof TextNote)
				nText++;
			if (note instanceof ImageNote)
				nImage++;
		}
		
		return name + ":" + nText + ":" + nImage;

	}
	
	@Override
	public int compareTo(Folder o) {
		return name.compareTo(o.name);
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		ArrayList<Note> NoteKeywords = new ArrayList<Note>();
		keywords = keywords.toLowerCase();
		boolean containing = true;
		String[] Splittedkeywords = keywords.split(" ");
		for (Note note : notes){
			{
				containing = true;
				for(int i=0;i<Splittedkeywords.length;i++)
				{
					if (Splittedkeywords[i].equals("or")) 
						continue;
					if (note instanceof ImageNote) {
						if(!(note.getTitle().toLowerCase().contains(Splittedkeywords[i])))
						{
							if((i+2<Splittedkeywords.length)&&Splittedkeywords[i+1].equals("or"))
								continue;
							containing = false;
							break;
						}else if((i+2<Splittedkeywords.length)&&Splittedkeywords[i+1].equals("or")) {
							i += 2;
							continue;
						}
					}else if(note instanceof TextNote) {
						TextNote textnote = (TextNote)note;
						if(!(textnote.getTitle().toLowerCase().contains(Splittedkeywords[i])||textnote.content.toLowerCase().contains(Splittedkeywords[i])))
						{
							if((i+2<Splittedkeywords.length)&&Splittedkeywords[i+1].equals("or"))
								continue;
							containing = false;
							break;
						}else if((i+2<Splittedkeywords.length)&&Splittedkeywords[i+1].equals("or")) {
							i += 2;
							continue;
						}
					}
				}
				if(containing)
					NoteKeywords.add(note);
			}
				
		}
		return NoteKeywords;
	}
}
