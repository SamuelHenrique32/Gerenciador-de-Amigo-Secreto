package modelo;

import java.util.Comparator;

public class MensagemEnviadaComparator implements Comparator<Participante>{

	@Override
	public int compare(Participante p1, Participante p2) {
		if(p1.quantMsgEnviadas() > p2.quantMsgEnviadas()) {
			return -1;
		}
		return 0;
	}

}
