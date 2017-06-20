package group1.go.Model;


/*
 * This class emulates a board with 6 longs.
 * It uses the first 31 bits of the long to determine whether a tile is at the position index
 * or not. 
 * The index 0 correspond to the least significant byte of the long and the index 31 to the most
 * significant byte. 
 * If there is a 1 at the position index then there is an object at that position.
 */
public class BitBoard {
	private long first30;
	private long second30;
	private long third30;
	private long fourth30;
	private long fifth30;
	private long sixth30;
	
	public BitBoard(){
		first30 = 0;
		second30 = 0;
		third30 = 0;
		fourth30 = 0;
		fifth30 = 0;
		sixth30 = 0;
	}
	
	public BitBoard clone(){
		return new BitBoard(first30,second30, third30, fourth30, fifth30, sixth30);
	}
	private BitBoard( long first30,long second30, long third30, long fourth30, long fifth30, long sixth30){
		this.first30=first30;
		this.second30=second30;
		this.third30=third30;
		this.fourth30=fourth30;
		this.fifth30=fifth30;
		this.sixth30=sixth30;
	}
	
	public boolean isInPosition(int index){
		if(index<0 ||index>=(Constants.BOARDSIZE+1)*(Constants.BOARDSIZE+1)){
			return false;
		}
		if(index < 31){
			return (first30&(1<<index)) == (1<<index);
		}
		if(index<62){
			index-=31;
			return (second30&(1<<index)) == (1<<index);
		}
		if(index<93){
			index-=62;
			return (third30&(1<<index)) == (1<<index);
		}
		if(index<124){
			index-=93;
			return (fourth30&(1<<index)) == (1<<index);
		}
		if(index<155){
			index-=124;
			return (fifth30&(1<<index)) == (1<<index);
		}
		index-=155;
		return (sixth30&(1<<index)) == (1<<index);
		
	}
	
	
	public void add(int index){
		if(index<0 ||index>=(Constants.BOARDSIZE+1)*(Constants.BOARDSIZE+1)){
			return;
		}
		if(index < 31){
			first30 = (first30|(1<<index));
			return;
		}
		if(index<62){
			index-=31;
			second30 = (second30|(1<<index));
			return;
		}
		if(index<93){
			index-=62;
			third30 = (third30|(1<<index));
			return;
		}
		if(index<124){
			index-=93;
			fourth30 = (fourth30|(1<<index));
			return;
		}
		if(index<155){
			index-=124;
			fifth30 = (fifth30|(1<<index));
			return;
		}
		index-=155;
		sixth30 = (sixth30|(1<<index));
		return;
	}

	public void remove(int index){
		if(index<0 ||index>=(Constants.BOARDSIZE+1)*(Constants.BOARDSIZE+1)){
			return;
		}
		long l; 
		if(index < 31){
			l=(Long.MAX_VALUE<<(index+1))|(Long.MAX_VALUE>>(63-index));
			first30 = first30&l;
			return;
		}
		if(index<62){
			index-=31;
			l =(Long.MAX_VALUE<<(index+1))|(Long.MAX_VALUE>>(63-index));
			second30 = second30&l;
			return;
		}
		if(index<93){
			index-=62;
			l = (Long.MAX_VALUE<<(index+1))|(Long.MAX_VALUE>>(63-index));
			third30 = third30&l;
			return;
		}
		if(index<124){
			index-=93;
			l = (Long.MAX_VALUE<<(index+1))|(Long.MAX_VALUE>>(63-index));
			fourth30 = fourth30&l;
			return;
		}
		if(index<155){
			index-=124;
			l = (Long.MAX_VALUE<<(index+1))|(Long.MAX_VALUE>>(63-index));
			fifth30 = fifth30&l;
			return;
		}
		index-=155;
		l = (Long.MAX_VALUE<<(index+1))|(Long.MAX_VALUE>>(63-index));
		sixth30 = sixth30&l;
		return;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (fifth30 ^ (fifth30 >>> 32));
		result = prime * result + (int) (first30 ^ (first30 >>> 32));
		result = prime * result + (int) (fourth30 ^ (fourth30 >>> 32));
		result = prime * result + (int) (second30 ^ (second30 >>> 32));
		result = prime * result + (int) (sixth30 ^ (sixth30 >>> 32));
		result = prime * result + (int) (third30 ^ (third30 >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().equals(obj.getClass()))
			return false;
		BitBoard other = (BitBoard) obj;
		if (fifth30 != other.fifth30)
			return false;
		if (first30 != other.first30)
			return false;
		if (fourth30 != other.fourth30)
			return false;
		if (second30 != other.second30)
			return false;
		if (sixth30 != other.sixth30)
			return false;
		if (third30 != other.third30)
			return false;
		return true;
	}

	public long getFirst30() {
		return first30;
	}

	public long getSecond30() {
		return second30;
	}

	public long getThird30() {
		return third30;
	}

	public long getFourth30() {
		return fourth30;
	}

	public long getFifth30() {
		return fifth30;
	}

	public long getSixth30() {
		return sixth30;
	}

	
	
	
}
