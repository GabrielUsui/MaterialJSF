package br.ufrn.imd.material.controllers;

import java.io.Serializable;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import br.ufrn.imd.material.dominio.Material;
import br.ufrn.imd.material.repositorios.MaterialRepositorio;

public class MaterialBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Material material;
	
	private DataModel<Material> materiaisModel;

	@Inject
	private UsuarioBean usuarioBean;
	
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
		materiaisModel = new ListDataModel<Material>(MaterialRepositorio.listarMateriais());
		return "/pages/material/list.jsf";
	}
	
	public String cadastrarMaterial()
	{
		material.setUsuarioCadastro(usuarioBean.getUsuarioLogado());
		MaterialRepositorio.adicionar(material);
		material = new Material();
		return "/pages/material/form.jsf";
	}
	
	public String removerMaterial() 
	{
		Material materialRemovido = materiaisModel.getRowData();
		MaterialRepositorio.remover(materialRemovido);
		materiaisModel = new ListDataModel<Material>(MaterialRepositorio.listarMateriais());
		return "/pages/material/list.jsf";
	}
	
}
