package model;

import java.util.Date;

public class Connection {
	private Date date;
	private ConnectionDestination from;
	private ConnectionDestination to;
	private String train;
	private ConnectionCapacity capacity;
	private String note;

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the from
	 */
	public ConnectionDestination getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(ConnectionDestination from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public ConnectionDestination getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(ConnectionDestination to) {
		this.to = to;
	}

	/**
	 * @return the train
	 */
	public String getTrain() {
		return train;
	}

	/**
	 * @param train
	 *            the train to set
	 */
	public void setTrain(String train) {
		this.train = train;
	}

	/**
	 * @return the capacity
	 */
	public ConnectionCapacity getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(ConnectionCapacity capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
}
