//components/RichTextEditor.jsx
import { useEditor, EditorContent } from '@tiptap/react';
import StarterKit from '@tiptap/starter-kit';

// 🛠️ 툴바 컴포넌트
const Toolbar = ({ editor }) => {
  if (!editor) return null;

  return (
    <div className="flex gap-2 border-b p-2 bg-gray-50">
      <button
        onClick={() => editor.chain().focus().toggleBold().run()}
        className={editor.isActive('bold') ? 'font-bold text-blue-600' : ''}
      >
        Bold
      </button>
      <button
        onClick={() => editor.chain().focus().toggleItalic().run()}
        className={editor.isActive('italic') ? 'italic text-blue-600' : ''}
      >
        Italic
      </button>
      <button
        onClick={() => editor.chain().focus().toggleBulletList().run()}
        className={editor.isActive('bulletList') ? 'text-blue-600' : ''}
      >
        Bullet List
      </button>
      <button
        onClick={() => editor.chain().focus().toggleHeading({ level: 2 }).run()}
        className={editor.isActive('heading', { level: 2 }) ? 'text-blue-600' : ''}
      >
        H2
      </button>
    </div>
  );
};

// 📝 에디터 본체
const RichTextEditor = ({ value, onChange }) => {
  const editor = useEditor({
    extensions: [StarterKit],
    content: value || '<p style="color: #6c757d;">여기에 내용을 입력하세요...</p>',
    onUpdate: ({ editor }) => onChange(editor.getHTML()),
  });

  return (
    <div
      className="border p-0 rounded shadow"
      style={{
        border: '1px solid #ced4da',
        borderRadius: '0.375rem',
        backgroundColor: '#fff',
      }}
    >
      <Toolbar editor={editor} />
      <div style={{ padding: '1rem', minHeight: '200px' }}>
        <EditorContent editor={editor} />
      </div>
    </div>
  );
};

export default RichTextEditor;
