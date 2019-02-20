package model.vo;

/**
 * Representation of a MovingViolation object
 */
public class VOMovingViolation implements Comparable<VOMovingViolation> {

	// TODO Definir los atributos de una infraccion
	private int id;
	private String loc;
	private String date;
	private int total;
	private String indicator;
	private String description;

	/**
	 * Metodo constructor
	 */
	public VOMovingViolation(int pId, String pLoc, String pDate, int pTotal, String pIndicator, String pDescription){

		id= pId;
		loc = pLoc;
		date = pDate;
		total = pTotal;
		indicator = pIndicator;
		description = pDescription;
	}	

	/**
	 * @return id - Identificador unico de la infraccion
	 */
	public int objectId() {
		// TODO Auto-generated method stub
		return id;
	}	


	/**
	 * @return location - Direccion en formato de texto.
	 */
	public String getLocation() {
		// TODO Auto-generated method stub
		return loc;
	}

	/**
	 * @return date - Fecha cuando se puso la infraccion .
	 */
	public String getTicketIssueDate() {
		// TODO Auto-generated method stub
		return date;
	}

	/**
	 * @return totalPaid - Cuanto dinero efectivamente paga el que recibio la infraccion en USD.
	 */
	public int getTotalPaid() {
		// TODO Auto-generated method stub
		return total;
	}

	/**
	 * @return accidentIndicator - Si hubo un accidente o no.
	 */
	public String  getAccidentIndicator() {
		// TODO Auto-generated method stub
		return indicator;
	}

	/**
	 * @return description - Descripcion textual de la infraccion.
	 */
	public String  getViolationDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public int compareTo(VOMovingViolation o) {
		// TODO implementar la comparacion "natural" de la clase
		if(Integer.parseInt(this.getTicketIssueDate())>Integer.parseInt(o.getTicketIssueDate()))
			return 1;
		else if(Integer.parseInt(this.getTicketIssueDate())<Integer.parseInt(o.getTicketIssueDate()))
			return -1;
		else
		{
			if(this.objectId()>o.objectId())
				return 1;
			else if(this.objectId()<o.objectId())
				return -1;
			return 0;
		}
	}

	public String toString()
	{
		// TODO Convertir objeto en String (representacion que se muestra en la consola)
		return " "+objectId();
	}
}
