package imb.pr3.estetica.service;

import imb.pr3.estetica.entity.Servicio;

import java.util.List;

public interface IServicioService {
    public List<Servicio> buscarServicio();

    public Servicio buscarServicioPorId(Integer id);

    public Servicio guardarServicio(Servicio cliente);

    public Servicio eliminarServicio(Integer id);

}