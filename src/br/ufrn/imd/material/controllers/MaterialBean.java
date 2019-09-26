package br.ufrn.imd.material.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrn.imd.material.dominio.Material;
import br.ufrn.imd.material.repositorios.MaterialRepositorio;

@Named
@SessionScoped
public class MaterialBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Material material;
	
	private DataModel<Material> materiaisModel;

	@Inject
	private UsuarioBean usuarioBean;
	
	@Inject
	private MaterialRepositorio materialRepositorio;
	
	public MaterialBean() 
	{
		material = new Material();
	}
	
	public String novoMaterial()
	{
		material = new Material();
		return "/pages/material/form.jsf";
	}
	
	public String listarMateriais() 
	{
		materiaisModel = new ListDataModel<Material>(materialRepositorio.listarMateriais());
		return "/pages/material/list.jsf";
	}
	
	public String cadastrarMaterial()
	{
		material.setUsuarioCadastro(usuarioBean.getUsuarioLogado());
		materialRepositorio.adicionar(material);
		material = new Material();
		return "/pages/material/form.jsf";
	}
	
	public String removerMaterial() 
	{
		Material materialRemovido = materiaisModel.getRowData();
		materialRepositorio.remover(materialRemovido);
		materiaisModel = new ListDataModel<Material>(materialRepositorio.listarMateriais());
		return "/pages/material/list.jsf";
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public DataModel<Material> getMateriaisModel() {
		return materiaisModel;
	}

	public void setMateriaisModel(DataModel<Material> materiaisModel) {
		this.materiaisModel = materiaisModel;
	}
	
}
