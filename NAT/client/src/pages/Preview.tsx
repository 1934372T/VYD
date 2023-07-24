import { useEffect, useState } from "react";
import { $axios } from "configs/axios";
import { AxiosError, AxiosResponse } from "axios";
import { useSearchParams } from "react-router-dom";

const Preview = () => {
  const [searchParams] = useSearchParams();
  const [file, setFile] = useState<string | null>(null);

  function base64ToBlob(base64: any, type = "application/octet-stream") {
    const binStr = atob(base64);
    const len = binStr.length;
    const arr = new Uint8Array(len);

    for (let i = 0; i < len; i++) {
      arr[i] = binStr.charCodeAt(i);
    }

    return new Blob([arr], { type });
  }

  useEffect(() => {
    let fileType = searchParams.get("file-type");
    let id = searchParams.get("id");
    $axios()
      .get(`${fileType}/?id=${id}`)
      .then((res: AxiosResponse) => {
        const { data } = res;
        const pdfBlob = base64ToBlob(data, "application/pdf");
        const pdfUrl = URL.createObjectURL(pdfBlob);
        setFile(pdfUrl);
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div style={{ width: "100vw", height: "100vh", padding: 0, margin: 0 }}>
      {file && (
        <embed
          src={file}
          type="application/pdf"
          style={{ width: "100%", height: "100%" }}
        />
      )}
    </div>
  );
};

export default Preview;
