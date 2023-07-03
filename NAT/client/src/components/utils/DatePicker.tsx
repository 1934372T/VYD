import dayjs, { Dayjs } from "dayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { useState } from "react";

interface DatePickerProps {
  onChangeDate: (v: string | undefined) => void;
}

export const DatePickers = (props: DatePickerProps) => {
  const { onChangeDate } = props;
  // ** State
  const [value, setValue] = useState<Dayjs | null>(dayjs());

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <DatePicker
        value={value}
        onChange={(newValue: Dayjs | null) => {
          setValue(newValue);
          onChangeDate(newValue?.toISOString());
        }}
        slotProps={{ textField: { size: "small" } }}
        sx={{
          width: "100%",
        }}
      />
    </LocalizationProvider>
  );
};
