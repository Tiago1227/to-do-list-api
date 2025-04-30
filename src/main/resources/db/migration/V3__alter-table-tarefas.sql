ALTER TABLE tarefas
ADD COLUMN usuario_id BIGINT;

ALTER TABLE tarefas
ADD CONSTRAINT fk_tarefas_usuarios
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id);
