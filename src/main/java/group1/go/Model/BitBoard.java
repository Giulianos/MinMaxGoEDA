package group1.go.Model;

public class BitBoard {
	long first30;
	long second30;
	long third30;
	long fourth30;
	long fifth30;
	long sixth30;
	
	public BitBoard(){
		first30 = 0;
		second30 = 0;
		third30 = 0;
		fourth30 = 0;
		fifth30 = 0;
		sixth30 = 0;
	}
	
	public boolean isInPosition(int index){
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
	
	
	public static void main(String[] args) {
		long first64 = 0;
		first64 = (first64|(1<<3));
		System.out.println(Long.toBinaryString(first64));
		first64 = (first64|(1<<6));
		System.out.println(Long.toBinaryString(first64));
		boolean rta = (first64&(1<<7)) == (1<<7);
		System.out.println(rta);
		System.out.println(Long.toBinaryString(first64&(1<<4)));
		System.out.println(Long.toBinaryString(first64&(1<<3)));
		first64 = (first64|(1<<3));
		first64 = (first64|(1<<6));
		long numb = Long.MAX_VALUE;
		System.out.println(Long.toBinaryString(numb>>(63-4)));
		System.out.println(Long.toBinaryString((numb<<(4))|(numb>>(63-3))));
		System.out.println(Long.toBinaryString((numb<<(4))));
		long l = (numb<<(4))|(numb>>(63-3));
		System.out.println("aca va la posta");
		System.out.println(Long.toBinaryString(l));
		System.out.println(Long.toBinaryString(first64));
		System.out.println(Long.toBinaryString(first64&l));
		System.out.println(Long.toBinaryString(-1<<36));
		System.out.println(Long.toBinaryString((1<<30)));
		long unsinged = ((-1<<30)^(-1<<31))^0;
		System.out.println(Long.toBinaryString((unsinged)));

		/*
		BitBoard bit = new BitBoard();
		bit.add(89);
		bit.add(0);
		bit.add(3);
		bit.add(64);
		bit.add(128);
		bit.add(178);
		bit.add(190);
		bit.add(145);
		bit.add(5);
		bit.add(7);
		bit.add(1);
		bit.add(2);
		
		System.out.println(Long.toBinaryString(bit.first64));
		System.out.println(Long.toBinaryString(bit.second64));
		System.out.println(Long.toBinaryString(bit.third64));
		System.out.println(Long.toBinaryString(Long.MAX_VALUE));
		System.out.println(bit.isInPosition(89));*/
	}
	
	
}
