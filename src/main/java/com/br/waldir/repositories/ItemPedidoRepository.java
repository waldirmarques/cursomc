package com.br.waldir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.waldir.domain.ItemPedido;

@Repository		//nome da classe que vc quer do bando mais o tipo da chave dele.
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
