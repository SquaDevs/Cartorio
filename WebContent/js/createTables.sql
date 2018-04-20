CREATE TABLE IF NOT EXISTS servico (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  sigla VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS subservico (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  sigla VARCHAR(45) NOT NULL,
  ordem VARCHAR(45) NOT NULL,
  id_servico INT NOT NULL,
  PRIMARY KEY (id, id_servico),
  INDEX fk_subservico_servico_idx (id_servico ASC),
  CONSTRAINT fk_subservico_servico
    FOREIGN KEY (id_servico)
    REFERENCES servico (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS senha (
  id INT NOT NULL AUTO_INCREMENT, 
  numero INT NOT NULL,
  prioridade VARCHAR(45) NOT NULL,
  id_servico INT NOT NULL,
  PRIMARY KEY (id, id_servico),
  INDEX fk_senha_servico1_idx (id_servico ASC),
  CONSTRAINT fk_senha_servico1
    FOREIGN KEY (id_servico)
    REFERENCES servico (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS atendimento (
  id INT NOT NULL AUTO_INCREMENT,
  status VARCHAR(45) NOT NULL,
  data_inicio DATETIME NOT NULL,
  data_fim DATETIME,
  id_subservico INT NOT NULL,
  id_senha INT NOT NULL,
  PRIMARY KEY (id, id_subservico, id_senha),
  INDEX fk_atendimento_subservico1_idx (id_subservico ASC),
  INDEX fk_atendimento_senha1_idx (id_senha ASC),
  CONSTRAINT fk_atendimento_subservico1
    FOREIGN KEY (id_subservico)
    REFERENCES subservico (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_atendimento_senha1
    FOREIGN KEY (id_senha)
    REFERENCES senha (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

