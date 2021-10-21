export class Filtro {
  id: number;
  campoId: string;
  estado: string;
  fechaInicio: string;
  fechaFin: string;
  texto: string;

  getFiltro() {
    let filtro = '';
    if (this.id) {
      filtro += this.campoId + '=' + this.id + '&';
    }
    if (this.estado && this.estado.length > 0) {
      filtro += 'estado=' + this.estado + '&';
    }
    if (this.fechaInicio && this.fechaInicio.length > 0) {
      filtro += 'fechaInicio=' + this.fechaInicio + '&fechaFin=' + this.fechaFin + '&';
    }
    if (this.texto) {
      filtro += 'texto=' + this.texto + '&';
    }
    filtro = filtro.substr(0, filtro.length -1);

    return filtro;
  }
}
