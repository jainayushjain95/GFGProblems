package lc.sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SatisfiabilityOfEquations {

	public static void main(String[] args) {
		String[] equations = {
				"a==b","b!=c","c==a"
		};
		
		System.out.println((new SatisfiabilityOfEquations().equationsPossible(equations)));

	}

	public boolean equationsPossible(String[] equations) {
		List<Integer>[] adjaceny = initializeAdjaceny(equations);
		int[] vertexColor = new int[26];
		Arrays.fill(vertexColor, -1);

		for(int i = 0;i < 26; i++) {
			if(vertexColor[i] == -1) {
				triggerDFS(adjaceny, i, vertexColor, i);
			}
		}

		for(String equation : equations) {
			if(equation.charAt(1) != '=') {
				int sourceIndex = getIntFromChar(equation.charAt(0));
				int destinationIndex = getIntFromChar(equation.charAt(3));
				if(sourceIndex == destinationIndex 
						|| (vertexColor[sourceIndex] != -1 && vertexColor[sourceIndex] == vertexColor[destinationIndex])) {
					return false;
				}
			}
		}

		return true;
	}

	public void triggerDFS(List<Integer>[] adjaceny, int sourceVertex, int[] vertexColor, int color) {
		vertexColor[sourceVertex] = color;
		if(adjaceny[sourceVertex] != null) {
			for(int adjacent : adjaceny[sourceVertex]) {
				if(vertexColor[adjacent] == -1) {
					triggerDFS(adjaceny, adjacent, vertexColor, color);
				}
			}	
		}
	}

	public List<Integer>[] initializeAdjaceny(String[] equations) {
		List<Integer>[] adjaceny = new ArrayList[26];

		for(String equation : equations) {
			if(equation.charAt(1) == '=') {
				int sourceIndex = getIntFromChar(equation.charAt(0));
				int destinationIndex = getIntFromChar(equation.charAt(3));

				if(adjaceny[sourceIndex] == null) {
					adjaceny[sourceIndex] = new ArrayList<Integer>();
				}

				if(adjaceny[destinationIndex] == null) {
					adjaceny[destinationIndex] = new ArrayList<Integer>();
				}

				adjaceny[sourceIndex].add(destinationIndex);
				adjaceny[destinationIndex].add(sourceIndex);
			}
		}
		return adjaceny;
	} 

	public int getIntFromChar(char c) {
		return c - 'a';
	}
}
