package med.voll.api.domain.medico;

public record DadosListagemMedico(
        Long id,
        String nome,
        String emil,
        String telefone,
        String crm,
        Especialidade especialidade) {

    public DadosListagemMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade());
    }
}
