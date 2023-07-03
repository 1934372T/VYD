import { TextField } from "@mui/material";

interface MultiTextFormProps {
  onChange?: (v: any) => void;
}

export const MultiTextForm = (props: MultiTextFormProps) => {
  const { onChange } = props;
  return (
    <TextField
      fullWidth
      size="small"
      type={"string"}
      rows={5}
      multiline
      onChange={(event) => {
        if (onChange) {
          onChange(event.target.value);
        }
      }}
    />
  );
};
