package com.redartedgames.ball.myobjects;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MovesData {
	public ArrayList<BigDecimal> accelerationsX, accelerationsY;
	
	public MovesData() {
		accelerationsX = new ArrayList<BigDecimal>();
		accelerationsY = new ArrayList<BigDecimal>();
	}
	
	public void addMove(BigDecimal accX, BigDecimal accY) {
		BigDecimal tempX = accX;
		BigDecimal tempY = accY;
		accelerationsX.add(tempX);
		accelerationsY.add(tempY);
	}
	
	public void removeMove() {
		if (accelerationsX.size() > 0) {
		accelerationsX.remove(accelerationsX.size()-1);
		accelerationsY.remove(accelerationsY.size()-1);
		}
	}
	
	public BigDecimal getLastAccX() {
		if (accelerationsX.size() > 0) {
			return accelerationsX.get(accelerationsX.size()-1);
		}
		else {
			return BigDecimal.ZERO;
		}
	}
	
	public BigDecimal getLastAccY() {
		if (accelerationsX.size() > 0) {
			return accelerationsY.get(accelerationsY.size()-1);
		}
		else {
			return BigDecimal.ZERO;
		}
	}
	
	public void addToLastAccX(BigDecimal accX) {
		BigDecimal temp = getLastAccX();
		temp = temp.add(accX);
		accelerationsX.remove(getLastAccX());
		accelerationsX.add(temp);
	}
	
	public void addToLastAccY(BigDecimal accY) {
		BigDecimal temp = getLastAccY();
		temp = temp.add(accY);
		accelerationsY.remove(getLastAccY());
		accelerationsY.add(temp);
	}
	
	public void pastePlayerMoves(MovesData movesData) {
		accelerationsX = new ArrayList<BigDecimal>();
		for(BigDecimal accX : movesData.accelerationsX) {
			accelerationsX.add(accX);
		}
		
		accelerationsY = new ArrayList<BigDecimal>();
		for(BigDecimal accY : movesData.accelerationsY) {
			accelerationsY.add(accY);
		}
	}
	
	public void shortenMovesDataToNumb(int numb) {
		int size = accelerationsX.size();
		for (int i = 0 ; i < size - numb; i++) {
			accelerationsX.remove(0);
		}
		size = accelerationsY.size();
		for (int i = 0 ; i < size - numb; i++) {
			accelerationsY.remove(0);
		}
	}
}
