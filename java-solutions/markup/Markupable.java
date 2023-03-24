package markup;

public interface Markupable {
    public void toMarkdown(StringBuilder stringBuilder);

    public void toTex(StringBuilder stringBuilder);
}
